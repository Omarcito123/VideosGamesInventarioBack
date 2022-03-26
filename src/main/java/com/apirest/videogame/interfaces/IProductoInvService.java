package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ProductoInv;
import com.apirest.videogame.model.ResponseObject;

public interface IProductoInvService {
	public ResponseObject createProduct(ProductoInv product);
	public ResponseObject saveProductListInv(ProductoInv[] product);
    public ResponseObject getListProductsBySucursal(ProductoInv product);
    public ResponseObject getListProductsBySucursalAndCategoria(ProductoInv product);
    public ResponseObject getRepuestoBodegaAndCategoria(ProductoInv product);
    public ResponseObject findProductByNameAndSucursalAndCode(ProductoInv product);
    public ResponseObject findProductbyName(ProductoInv product);
    public ResponseObject findProductById(ProductoInv product);
    public ProductoInv findProductbyName(long idsucursal, String productName);
    public ResponseObject editProduct(ProductoInv product);
    public ResponseObject deleteProductsById(ProductoInv product);
    public ResponseObject traslaProductoSucursal(ProductoInv product);
	public ResponseObject eliminarInventario(ProductoInv producto);
	public ProductoInv findProductByCodeAndSucursalAndName(long idsucursal, String serie, String name);
	public ResponseObject traslaProductoSucursales(ProductoInv product);
	public ResponseObject updateExistenciasBodega(ProductoInv product);
}