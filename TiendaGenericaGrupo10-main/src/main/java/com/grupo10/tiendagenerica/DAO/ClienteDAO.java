package com.grupo10.tiendagenerica.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.grupo10.tiendagenerica.DTO.ClienteVO;

//Clase que permite el acceso a la base de datos
public class ClienteDAO {

	// Registrar un nuevo usuario

	public void registrarCliente(ClienteVO user) {
		// llama y crea una instancia de la clase encargada de hacer la conexión
		Conexion conex = new Conexion();

		try {
			// sentencia que se ejecutara en la base de datos
			Statement estatuto = conex.getConnection().createStatement();

			// String que contiene la sentencia insert a ejecutar
			String sentencia = "INSERT INTO clientes VALUES(" + user.getCedula_cliente() + "," + "'"
					+ user.getDireccion_cliente() + "'," + "'" + user.getEmail_cliente() + "'," + "'"
					+ user.getNombre_cliente() + "'," + "'" + user.getTelefono_cliente() + "'" + ");";

			// se ejecuta la sentencia en la base de datos
			estatuto.executeUpdate(sentencia);

			// impresión en consola para verificación
			System.out.println("Registrado " + sentencia);

			// cerrando la sentencia y la conexión
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			// si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo insertar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			// si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo insertar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}// fin registrar cliente

	/**
	 * permite consultar el Usuario asociado al user enviado como parametro
	 * 
	 * @param documento
	 * @return
	 */
	public ArrayList<ClienteVO> consultarCliente(String cliente) {

		// lista que contendra el o los usuarios obtenidos
		ArrayList<ClienteVO> listaclientes = new ArrayList<ClienteVO>();

		// instancia de la conexión
		Conexion conex = new Conexion();

		try {
			// prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection()
					.prepareStatement("SELECT * FROM clientes where cedula_cliente  = ? ");

			// se cambia el comodin ? por el dato que ha llegado en el parametro de la
			// funcion
			consulta.setString(1, cliente);

			// ejecute la sentencia
			ResultSet res = consulta.executeQuery();

			// cree un objeto basado en la clase entidad con los datos encontrados
			if (res.next()) {
				ClienteVO Cliente = new ClienteVO();
				Cliente.setCedula_cliente(Integer.parseInt(res.getString("cedula_cliente")));
				Cliente.setDireccion_cliente(res.getString("direccion_cliente"));
				Cliente.setEmail_cliente(res.getString("email_cliente"));
				Cliente.setNombre_cliente(res.getString("nombre_cliente"));
				Cliente.setTelefono_cliente(res.getString("telefono_cliente"));

				listaclientes.add(Cliente);
			}

			// cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			// si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			// si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		return listaclientes;
	}// fin consultar clientes

	/**
	 * permite consultar la lista de todos los usuarios
	 * 
	 * @return
	 */
	public ArrayList<ClienteVO> listaDeClientes() {
		// lista que contendra el o los usuarios obtenidos
		ArrayList<ClienteVO> listaclientes = new ArrayList<ClienteVO>();

		// instancia de la conexión
		Conexion conex = new Conexion();

		try {
			// prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM clientes");

			// ejecute la sentencia
			ResultSet res = consulta.executeQuery();

			// cree un objeto para cada encontrado en la base de datos basado en la clase
			// entidad con los datos encontrados
			while (res.next()) {
				ClienteVO Cliente = new ClienteVO();
				Cliente.setCedula_cliente(Integer.parseInt(res.getString("cedula_cliente")));
				Cliente.setDireccion_cliente(res.getString("direccion_cliente"));
				Cliente.setEmail_cliente(res.getString("email_cliente"));
				Cliente.setNombre_cliente(res.getString("nombre_cliente"));
				Cliente.setTelefono_cliente(res.getString("telefono_cliente"));

				listaclientes.add(Cliente);
			}

			// cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			// si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todos los clientes");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			// si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todos los clientes");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

		return listaclientes;
	}// mostrarclientes

	public void eliminarCliente(Integer cedula_cliente) {

		// instancia de la conexion
		Conexion conex = new Conexion();

		try {
			// sentencia inicializada
			Statement consulta = conex.getConnection().createStatement();

			// preparando sentencia a realizar
			String sentencia = "delete from clientes where cedula_cliente=" + cedula_cliente + ";";

			// impresion de verificación
			System.out.println("Registrado " + sentencia);

			// ejecutando la sentencia en la base de datos
			consulta.execute(sentencia);

			// cerrando sentencia y conexión
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			// si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			// si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}// eliminar cliente

	public void actualizarCliente(ClienteVO user) {

		// instancia de conexion
		Conexion conex = new Conexion();

		try {
			// inicializando sentencia
			Statement estatuto = conex.getConnection().createStatement();

			// String con la sentencia a ejecutar
			String sentencia = "UPDATE clientes " + "SET direccion_cliente = '" + user.getDireccion_cliente() + "',"
					+ "email_cliente = '" + user.getEmail_cliente() + "'," + "nombre_cliente = '"
					+ user.getNombre_cliente() + "'," + "telefono_cliente = '" + user.getTelefono_cliente() + "' "
					+ "WHERE cedula_cliente = " + user.getCedula_cliente() + ";";

			// ejecuta la sentencia
			estatuto.executeUpdate(sentencia);

			// verificación por consola de la sentencia
			System.out.println("Registrado " + sentencia);

			// cerrando sentencia y conexión
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			// si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo actualizar  el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			// si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar el cliente");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}// actualizar cliente

}
