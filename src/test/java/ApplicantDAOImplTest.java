import com.orion.dao.ApplicantDAOImpl;
import com.orion.model.Applicant;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

public class ApplicantDAOImplTest {
    private static final String HBM_DIR_PREFIX = "./src";
    private ApplicantDAOImpl applicantDAO = new ApplicantDAOImpl();

    public ApplicantDAOImplTest(){
        Configuration configuration = new Configuration();
        configuration.setProperty(Environment.DRIVER,"com.mysql.jdbc.Driver");
        configuration.setProperty(Environment.URL,"jdbc:mysql://localhost:3306/orion_applicants");
        configuration.setProperty(Environment.USER,"root");
        configuration.setProperty(Environment.PASS,"root");
        configuration.setProperty(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
        configuration.setProperty(Environment.SHOW_SQL,"true");
        configuration.setProperty(Environment.CACHE_PROVIDER,"org.hibernate.cache.NoCacheProvider");
        //configuration.setProperty(Environment.HBM2DDL_AUTO,"create-drop");
        configuration.addFile(HBM_DIR_PREFIX+ "/main/resources/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);

         //   applicantDAO.setHibernateTemplate(hibernateTemplate);
        applicantDAO.setSessionFactory(sessionFactory);

        }

    public static void main(String[] args) {
        ApplicantDAOImplTest applicantDAOImplTest = new ApplicantDAOImplTest();
        List<Applicant> listApp = applicantDAOImplTest.applicantDAO.listApplicant();
        listApp.forEach((v)->{
            System.out.println(v.toString());
        });
    }

}
