import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import controller.AdminController;
import dao.TransportDao;
import dao.TransportDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Transport;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import util.DBConnectionPool;
import util.ScriptRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class AdminTest{

    private UserDao userDao;
    private TransportDao transportDao;
    private AdminController controller = new AdminController();
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Before
    public void init() {
        DBConnectionPool cp = DBConnectionPool.getInstance();
        try (Connection connection = cp.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection,
                    false, true);

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/createTables.sql"));
            scriptRunner.runScript(reader);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDao = new UserDaoImpl();
        transportDao = new TransportDaoImpl();
    }

    @Test
    public void testEmptyAction() throws Exception {
        ArgumentCaptor<List> captorUser = ArgumentCaptor.forClass(List.class);
        List<User> list = userDao.getAll();

        when(request.getParameter("action")).thenReturn("");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        controller.doGet(request, response);
        verify(request).getRequestDispatcher("app/Admin.jsp");
        verify(dispatcher).forward(request, response);
        verify(request).setAttribute(eq("users"), captorUser.capture());

        assertThat(list, is(captorUser.getValue()));
    }

    @Test
    public void testEditActionForNotDriver() throws Exception {
        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        User user = userDao.getBy(1L);

        when(request.getParameter("action")).thenReturn("edit");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        controller.doGet(request, response);
        verify(request).getRequestDispatcher("app/addUser.jsp");
        verify(dispatcher).forward(request, response);
        verify(request).setAttribute(eq("jspUser"), captorUser.capture());

        assertThat(user, is(captorUser.getValue()));

    }

    @Test
    public void testEditActionForDriver() throws Exception {
        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Transport> captorTransport = ArgumentCaptor.forClass(Transport.class);
        User user = userDao.getBy(2L);
        Transport transport = transportDao.getBy(user);

        when(request.getParameter("action")).thenReturn("edit");
        when(request.getParameter("id")).thenReturn("2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        controller.doGet(request, response);
        verify(request).getRequestDispatcher("app/addDriver.jsp");
        verify(dispatcher).forward(request, response);
        verify(request).setAttribute(eq("jspUser"), captorUser.capture());
        verify(request).setAttribute(eq("transport"), captorTransport.capture());

        assertThat(user, is(captorUser.getValue()));
        assertThat(transport, is(captorTransport.getValue()));
    }

    @Test
    public void testInsertManagerAction() throws Exception {

        when(request.getParameter("action")).thenReturn("insertManager");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        controller.doGet(request, response);

        verify(request).getRequestDispatcher("app/addUser.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testInsertDriverAction() throws Exception {

        when(request.getParameter("action")).thenReturn("insertDriver");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        controller.doGet(request, response);

        verify(request).getRequestDispatcher("app/addDriver.jsp");
        verify(dispatcher).forward(request, response);
    }

//    @Test
//    public void testEditUser() throws Exception {
//        User user = new User();
//        user.setId(0L);
//        user.setName("Admin1");
//        user.setLogin("admin1");
//        user.setPassword("admin1");
//        user.setRole(1L);
//
//        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
//        ArgumentCaptor<List> captorList = ArgumentCaptor.forClass(List.class);
//
//        when(request.getParameter("name")).thenReturn(user.getName());
//        when(request.getParameter("login")).thenReturn(user.getLogin());
//        when(request.getParameter("password")).thenReturn(user.getPassword());
//        when(request.getParameter("id")).thenReturn(user.getId().toString());
//        when(request.getParameter("role")).thenReturn(user.getRole().toString());
//        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
//        UserDaoImpl dao = mock(UserDaoImpl.class);
//
//        controller.doPost(request,response);
//        verify(dao).update(captorUser.capture());
//        verify(request).setAttribute(eq("users"),captorList.capture());
//        verify(request).getRequestDispatcher("app/Admin.jsp");
//        verify(dispatcher).forward(request, response);
//
//        assertThat(user, is(captorUser.getValue()));
//
//        List list = userDao.getAll();
//        userDao.update(user);
//        assertThat(list,is(captorList.getValue()));
//    }
}