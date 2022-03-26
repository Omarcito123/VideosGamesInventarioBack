package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IProductoInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ProductoInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Sucursales;
import com.apirest.videogame.model.UserInv;
import com.apirest.videogame.repository.ProductoInvRepository;
import com.apirest.videogame.repository.SucursalesRepository;
import com.apirest.videogame.repository.UserInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class ProductoInvService implements IProductoInvService {

	@Autowired
	ProductoInvRepository productoInvRepository;

	@Autowired
	UserInvRepository userInvRepository;
	
	@Autowired
	SucursalesRepository sucursalesRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createProduct(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<UserInv> user = userInvRepository.findById(product.getIduseradd());
			if (user.isPresent()) {
				if (user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					product.setDateadd(fechaSal.getFecha());
					productoInvRepository.save(product);
					response.setState("Success");
					response.setMessage("Producto creado exitosamente");
				} else {
					response.setState("Error");
					response.setMessage("Producto no pudo ser creado");
				}
			} else {
				response.setState("Error");
				response.setMessage("Producto no pudo ser creado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createProduct");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(product));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListProductsBySucursal(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		List<ProductoInv> productsList = new ArrayList<ProductoInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productsList = productoInvRepository.getListProductsBySucursal(product.getIdsucursal());
			response.setState("Success");
			response.setMessage("Inventario cargado exitosamente");
			response.setData(productsList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListProductsBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + product.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getListProductsBySucursalAndCategoria(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		List<ProductoInv> productsList = new ArrayList<ProductoInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productsList = productoInvRepository.getListProductsBySucursalAndCategoria(product.getIdsucursal(), product.getCategoria());
			response.setState("Success");
			response.setMessage("Inventario cargado exitosamente");
			response.setData(productsList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListProductsBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + product.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findProductbyName(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		ProductoInv productFind = new ProductoInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productFind = productoInvRepository.findProductByNameAndSucursal(product.getIdsucursal(), product.getNombre());
			response.setState("Success");
			response.setMessage("Producto encontrado exitosamente");
			response.setData(productFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findProductbyName");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + product.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ProductoInv findProductByCodeAndSucursalAndName(long idsucursal, String serie, String name) {
		ProductoInv productFind = new ProductoInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productFind = productoInvRepository.findProductByCodeAndSucursalAndName(idsucursal, serie, name);
			return productFind;
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findProductbyName");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + idsucursal + " serie: " + serie + " name: " + name);
			integrationLogService.save(error);
			return null;
		}
	}
	
	@Override
	public ResponseObject findProductByNameAndSucursalAndCode(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		ProductoInv productFind = new ProductoInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			if(product.getNombre() != null && !product.getNombre().equals("")) {
				productFind = productoInvRepository.findProductByNameAndSucursal(product.getIdsucursal(), product.getNombre());
			}else {
				productFind = productoInvRepository.findProductByCodeAndSucursal(product.getIdsucursal(), product.getSerie());
			}
			response.setState("Success");
			response.setMessage("Producto encontrado exitosamente");
			response.setData(productFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findProductbyName");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + product.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findProductById(ProductoInv productInv) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ProductoInv> product = productoInvRepository.findById(productInv.getIdprodinv());
			if(product.isPresent()) {
				response.setState("Success");
				response.setMessage("Producto encontrado exitosamente");
				response.setData(product);
			}else {
				response.setState("Error");
				response.setMessage("Producto no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findProductById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idprducto: " + productInv.getIdprodinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editProduct(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ProductoInv> productUpdate = productoInvRepository.findById(product.getIdprodinv());
			Optional<UserInv> user = userInvRepository.findById(product.getIduseradd());
			if (user.isPresent()) {
				if (user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					if (productUpdate.isPresent()) {
						product.setDatemod(fechaSal.getFecha());
						productoInvRepository.save(product);
						response.setState("Success");
						response.setMessage("Producto actualizado exitosamente");
					} else {
						response.setState("Error");
						response.setMessage("Producto no pudo ser actualizado");
					}
				}else {
					response.setState("Error");
					response.setMessage("Producto no pudo ser actualizado");
				}				
			} else {
				response.setState("Error");
				response.setMessage("Producto no pudo ser  actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editProduct");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(product));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteProductsById(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<UserInv> user = userInvRepository.findById(product.getIduseradd());
			if (user.isPresent()) {
				if (user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					productoInvRepository.deleteById(product.getIdprodinv());
					response.setState("Success");
					response.setMessage("Producto eliminado exitosamente");
				} else {
					response.setState("Error");
					response.setMessage("Producto no pudo sereliminado");
				}
			} else {
				response.setState("Error");
				response.setMessage("Producto no pudo ser eliminado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteProductsById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idproducto: " + product.getIdprodinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject traslaProductoSucursal(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ProductoInv productfind = productoInvRepository.findProductByNameAndSucursal(product.getIdsucursal(), product.getNombre());
			Optional<UserInv> user = userInvRepository.findById(product.getIduseradd());
			long idProduct = product.getIdprodinv();
			product.setIdprodinv(0);
			if (user.isPresent()) {
				if (user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					int numberProductTras = product.getExistencia();
					if (productfind != null) {						
						int total = productfind.getExistencia() + numberProductTras;						
						productfind.setExistencia(total);
						productfind.setDatemod(fechaSal.getFecha());
						productfind.setIdusermod(product.getIdusermod());
						productoInvRepository.save(productfind);
						
						Optional<ProductoInv> productUpdate = productoInvRepository.findById(idProduct);
						int newExist = productUpdate.get().getExistencia() - numberProductTras;
						productUpdate.get().setExistencia(newExist);
						productUpdate.get().setDatemod(fechaSal.getFecha());
						productUpdate.get().setIdusermod(product.getIdusermod());
						productoInvRepository.save(productUpdate.get());
						response.setState("Success");
						response.setMessage("Producto trasladado exitosamente");
					} else {
						Optional<ProductoInv> productfind2 = null;
						if(idProduct != 0) {
							productfind2 = productoInvRepository.findById(idProduct);
						}
						if(productfind2.get() != null) {
							product.setCategoria(productfind2.get().getCategoria());
							product.setSerie(productfind2.get().getSerie());
							product.setEstado(productfind2.get().getEstado());
						}
						product.setDateadd(fechaSal.getFecha());
						product.setIduseradd(product.getIdusermod());
						product.setDatemod(fechaSal.getFecha());
						product.setIdusermod(0);
						productoInvRepository.save(product);
						
						Optional<ProductoInv> productUpdate = productoInvRepository.findById(idProduct);
						int newExist = productUpdate.get().getExistencia() - numberProductTras;
						productUpdate.get().setExistencia(newExist);
						productUpdate.get().setDatemod(fechaSal.getFecha());
						productUpdate.get().setIdusermod(product.getIdusermod());
						productoInvRepository.save(productUpdate.get());
						response.setState("Success");
						response.setMessage("Producto trasladado exitosamente");
					}
				}else {
					response.setState("Error");
					response.setMessage("No estar permitido para realizar el traslado");
				}				
			}else {
				response.setState("Error");
				response.setMessage("No estar permitido para realizar el traslado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("traslaProductoSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(product));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject traslaProductoSucursales(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ProductoInv productfind = productoInvRepository.findProductByNameAndSucursal(product.getIdsucursal(), product.getNombre());
			Optional<UserInv> user = userInvRepository.findById(product.getIduseradd());
			long idProduct = product.getIdprodinv();
			product.setIdprodinv(0);
			if (user.isPresent()) {
				if (user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					int numberProductTras = product.getExistencia();
					if (productfind != null) {						
						int total = productfind.getExistencia() + numberProductTras;						
						productfind.setExistencia(total);
						productfind.setPreciocosto(product.getPreciocosto());
						productfind.setPrecioregular(product.getPrecioregular());
						productfind.setPreciooferta(product.getPreciooferta());
						productfind.setDatemod(fechaSal.getFecha());
						productfind.setIdusermod(product.getIdusermod());
						productoInvRepository.save(productfind);
						response.setState("Success");
						response.setMessage("Producto trasladado exitosamente");
					} else {
						Optional<ProductoInv> productfind2 = null;
						if(idProduct != 0) {
							productfind2 = productoInvRepository.findById(idProduct);
						}
						if(productfind2.get() != null) {
							product.setCategoria(productfind2.get().getCategoria());
							product.setSerie(productfind2.get().getSerie());
							product.setEstado(productfind2.get().getEstado());
						}
						product.setDateadd(fechaSal.getFecha());
						product.setIduseradd(product.getIdusermod());
						product.setDatemod(fechaSal.getFecha());
						product.setIdusermod(0);
						productoInvRepository.save(product);
						response.setState("Success");
						response.setMessage("Producto trasladado exitosamente");
					}
				}else {
					response.setState("Error");
					response.setMessage("No estar permitido para realizar el traslado");
				}				
			}else {
				response.setState("Error");
				response.setMessage("No estar permitido para realizar el traslado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("traslaProductoSucursales");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(product));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject updateExistenciasBodega(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			long idProduct = product.getIdprodinv();
			Optional<ProductoInv> productUpdate = productoInvRepository.findById(idProduct);
			
			if(productUpdate != null) {
				if(productUpdate.get() != null) {
					int numberProductTras = product.getExistencia();
					int newExist = productUpdate.get().getExistencia() - numberProductTras;
					productUpdate.get().setExistencia(newExist);
					productUpdate.get().setDatemod(fechaSal.getFecha());
					productUpdate.get().setIdusermod(product.getIdusermod());
					productoInvRepository.save(productUpdate.get());
					response.setState("Success");
					response.setMessage("Producto trasladado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Pedido no encontrado");
				}	
			}else {
				response.setState("Error");
				response.setMessage("Pedido no encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateExistenciasBodega");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idProducto: " + product.getIdprodinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ProductoInv findProductbyName(long idsucursal, String productName) {
		ProductoInv productFind = new ProductoInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productFind = productoInvRepository.findProductByNameAndSucursal(idsucursal, productName);
			return productFind;
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findProductbyName");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + idsucursal + " name: " + productName);
			integrationLogService.save(error);
			return null;
		}
	}

	@Override
	public ResponseObject saveProductListInv(ProductoInv[] productList) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			if(productList != null) {
				for (ProductoInv product: productList) {
					product.setDateadd(fechaSal.getFecha());
					productoInvRepository.save(product);
					response.setState("Success");
					response.setMessage("Producto creado exitosamente");				
				}
			}			
			response.setState("Success");
			response.setMessage("Venta creada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createVenta");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(productList));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getRepuestoBodegaAndCategoria(ProductoInv product) {
		ResponseObject response = new ResponseObject();
		List<ProductoInv> productsList = new ArrayList<ProductoInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		int idBodega = 0;
		try {
			List<Sucursales> sucursalesList = (List<Sucursales>) sucursalesRepository.findAll();
			for (Sucursales sucu : sucursalesList) 
	        {
	            if (sucu.getNombre().equals("Bodega"))
	            {
	                idBodega = (int) sucu.getIdsucursal();
	            }
	        }
			if(product.getCategoria() == null || product.getCategoria().equals("")) {
				productsList = productoInvRepository.getListProductsBySucursal(idBodega);
			}else {
				productsList = productoInvRepository.getRepuestoBodegaAndCategoria(idBodega, product.getCategoria());
			}			
			response.setState("Success");
			response.setMessage("Inventario cargado exitosamente");
			response.setData(productsList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getRepuestoBodegaAndCategoria");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + product.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject eliminarInventario(ProductoInv producto) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			productoInvRepository.eliminarInventario(producto.getIdsucursal());
			response.setState("Success");
			response.setMessage("Inventario eliminado exitosamente");			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("eliminarInventario");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + producto.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
