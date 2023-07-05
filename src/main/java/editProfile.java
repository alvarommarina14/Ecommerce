

import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Localidad;
import Entities.Provincia;
import data.DbHandlerProvincias;
import data.DbHandlerClientes;
import data.DbHandlerLocalidades;

/**
 * Servlet implementation class editProfile
 */
@WebServlet("/editProfile")
public class editProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String edit = request.getParameter("edit");
		System.out.println("edit "+edit);
		DbHandlerLocalidades db = new DbHandlerLocalidades();
		LinkedList<Localidad> localidades = db.selectLocalidad();
		request.setAttribute("localidades", localidades);
		if(edit == null) {
		request.getRequestDispatcher("editProfile.jsp").forward(request, response);
		} else if(edit.equals("edit")) {
			DbHandlerClientes dbCli = new DbHandlerClientes();
			Integer dni = Integer.parseInt(request.getParameter("dni"));
			String nombre = request.getParameter("nombre");
			String email = request.getParameter("email");
			String dia = request.getParameter("dia");
			String mes = request.getParameter("meses");
			String anio = request.getParameter("anio");
			String tel = request.getParameter("telefono");
			String direccion = request.getParameter("direccion");
			Integer codPostal = Integer.parseInt(request.getParameter("localidad"));
			String pw = request.getParameter("pw");
			String newpw = request.getParameter("newpw");
			String confnewpw = request.getParameter("confpw");
			
			String fecha = anio+"-"+mes+"-"+dia;
			Boolean verified = dbCli.checkPassword(dni, pw);
			if(verified &&  newpw.equals(confnewpw)) {
				dbCli.updatePassword(dni, newpw);
			}
			dbCli.updateClientUser(dni, nombre, direccion, codPostal, email, fecha);
			request.getRequestDispatcher("editProfile.jsp").forward(request, response);
		}
		
}
}
