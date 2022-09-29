package com.example.demo.utils;

import com.example.demo.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UtilsValid {
    protected void cpfIsValid(String cpf) {
        if (!this.checkIsOnlyNumbers(cpf)) {
            throw new ApiRequestException("CPF deve ter 11 digitos");
        }
    }


    protected static boolean checkIsOnlyNumbers(String str) {
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(str);

        return m.matches();
    }

}
