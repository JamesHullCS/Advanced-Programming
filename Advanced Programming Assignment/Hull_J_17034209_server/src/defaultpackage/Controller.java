package defaultpackage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;
/**
* This controls and runs the server and website.
* @author Student ID: 17034209 Name: James Alan-Abbott Hull.
*/
public class Controller {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server(8005); 
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");	
		ctx.setContextPath("/vehicles"); 

        ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
        ClassList classlist = ClassList.setServerDefault(server);
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration","org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		ctx.addServlet("servlets.Home", "/home"); 
		ctx.addServlet("servlets.Login", "/login");  
		ctx.addServlet("servlets.Logout", "/loggedout"); 
		ctx.addServlet("servlets.addVehicles", "/addVehicle"); 
		ctx.addServlet("servlets.ServletEdit", "/EditVehicles"); 
		ctx.addServlet("servlets.ServletDelete", "/deletevehicle"); 
		
		ctx.addServlet("servlets.ServletApi", "/api");
		
		server.setHandler(ctx); 
		server.start(); 
		server.join(); 

		
	}
}
