
package br.edu.iff.ControledeVendas.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SemEspacoValidator  implements ConstraintValidator<SemEspacoValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return true;
    }
    
}
