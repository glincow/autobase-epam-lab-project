import dao.RoleDao;
import model.Role;

public class test {
    public static void main(String[] args) {
        RoleDao roleDao = new RoleDao();
        roleDao.addRole(new Role("123123"));
    }
}
