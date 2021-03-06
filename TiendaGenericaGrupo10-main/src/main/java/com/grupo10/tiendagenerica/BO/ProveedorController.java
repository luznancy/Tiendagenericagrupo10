package com.grupo10.tiendagenerica.BO;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo10.tiendagenerica.DAO.ProveedorDAO;
import com.grupo10.tiendagenerica.DTO.ProveedorVO;

@RestController
public class ProveedorController {
	
	/*
	 * @GetMapping obtener o buscar
	 * @PostMapping insertar o agregar
	 * @PutMapping modificar o actualizar
	 * @DeleteMapping eliminar o borrar
	 * */
	
	@PostMapping("/registrarproveedor")
	public void registrarproveedor(ProveedorVO user) {
		ProveedorDAO Dao = new ProveedorDAO();
		Dao.registrarProveedor(user);	
		}
	
	@GetMapping("/consultarproveedores")
	public ArrayList<ProveedorVO> consultarproveedor(String proveedor) {
		ProveedorDAO Dao = new ProveedorDAO();
		return Dao.consultarProveedor(proveedor);
	}
	
	
	@GetMapping("/listarproveedores")
	public ArrayList<ProveedorVO> listaDeProveedores() {
		ProveedorDAO Dao = new ProveedorDAO();
		return Dao.listaDeProveedores();
	}
	
	@DeleteMapping("/eliminarproveedor")
	public void eliminarProveedor(Integer nit_proveedor) {
		ProveedorDAO Dao = new ProveedorDAO();
		Dao.eliminarProveedor(nit_proveedor);
	}
	
	@PutMapping("/actualizarproveedor")
	public void actualizarProveedor(ProveedorVO user) {
		ProveedorDAO Dao = new ProveedorDAO();
		Dao.actualizarProveedor(user);
	}

}
