import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.autsub.autsub.Company.Company;
import com.autsub.autsub.Company.CompanyRepository;
import com.autsub.autsub.Company.CompanyServiceImp;

@RunWith(MockitoJUnitRunner.class)
public class CompnayServiceUnitTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImp companyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterNewCompany() {
        Company company = new Company();
        company.setName("Test Company");
        company.setAddress("Test Address");
        company.setCommercial_Registration_Number("1234567890");
        company.setEmail("asdb@gmail.com");
        company.setIndustry("Finance");
        company.setPassword("fhbdjn897HJ");


        when(companyRepository.save(company)).thenReturn(company);

        RigterResponse result = companyService.registerNewCompany(company);

        assertEquals("Test Company", result.getName());
        verify(companyRepository).save(company);
    }
}