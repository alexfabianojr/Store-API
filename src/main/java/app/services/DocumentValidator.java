package app.services;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DocumentValidator {
    public static Boolean isCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        CPFFormatter formatter = new CPFFormatter();
        return cpfValidator.isEligible(formatter.unformat(cpf));
    }
}
