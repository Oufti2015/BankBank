package sst.bank.report.html;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DetailsPrinterTest {
    @Test
    public void test1() {
        String input = "DOMICILIATION EUROPEENNE DE LUMINUS SA NUMERO DE MANDAT : M038980259645";
        String expectedResult = "DOMICILIATION EUROPEENNE DE LUMINUS SA <BR><B>NUMERO DE MANDAT :</B> M038980259645";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test2() {
        String input = "DOMICILIATION EUROPEENNE DE LUMINUS SA NUMERO DE MANDAT : M038980259645 REFERENCE : 002112311934";
        String expectedResult = "DOMICILIATION EUROPEENNE DE LUMINUS SA <BR><B>NUMERO DE MANDAT :</B> M038980259645 <BR><B>REFERENCE :</B> 002112311934";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test3() {
        String input = "DOMICILIATION EUROPEENNE DE LUMINUS SA NUMERO DE MANDAT : M038980259645 REFERENCE : 002112311934 COMMUNICATION : LUMINUS ACOMPTE 6367275775";
        String expectedResult = "DOMICILIATION EUROPEENNE DE LUMINUS SA <BR><B>NUMERO DE MANDAT :</B> M038980259645 <BR><B>REFERENCE :</B> 002112311934 <BR><B>COMMUNICATION :</B> LUMINUS ACOMPTE 6367275775";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test4() {
        String input = "DOMICILIATION EUROPEENNE DE LUMINUS SA NUMERO DE MANDAT : M038980259645 REFERENCE : 002112311934 COMMUNICATION : LUMINUS ACOMPTE 6367275775 REFERENCE BANQUE : 2311070647028865";
        String expectedResult = "DOMICILIATION EUROPEENNE DE LUMINUS SA <BR><B>NUMERO DE MANDAT :</B> M038980259645 <BR><B>REFERENCE :</B> 002112311934 <BR><B>COMMUNICATION :</B> LUMINUS ACOMPTE 6367275775 <BR><B>REFERENCE BANQUE :</B> 2311070647028865";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test5() {
        String input = "DOMICILIATION EUROPEENNE DE LUMINUS SA NUMERO DE MANDAT : M038980259645 REFERENCE : 002112311934 COMMUNICATION : LUMINUS ACOMPTE 6367275775 REFERENCE BANQUE : 2311070647028865 DATE VALEUR : 07/11/2023";
        String expectedResult = "DOMICILIATION EUROPEENNE DE LUMINUS SA <BR><B>NUMERO DE MANDAT :</B> M038980259645 <BR><B>REFERENCE :</B> 002112311934 <BR><B>COMMUNICATION :</B> LUMINUS ACOMPTE 6367275775 <BR><B>REFERENCE BANQUE :</B> 2311070647028865 <BR><B>DATE VALEUR :</B> 07/11/2023";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void test7() {
        String input = "PAIEMENT AVEC LA CARTE DE DEBIT NUMERO 4871 04XX XXXX 6276 ARAMARK CLEARSTREAM LUXEMBOURG (G-D) 30/01/2023 VISA DEBIT - SANS CONTACT REFERENCE BANQUE : 2301311711585263 DATE VALEUR : 30/01/2023";
        String expectedResult = "PAIEMENT AVEC LA CARTE DE DEBIT NUMERO 4871 04XX XXXX 6276<BR> ARAMARK CLEARSTREAM LUXEMBOURG (G-D) 30/01/2023 VISA DEBIT - SANS CONTACT <BR><B>REFERENCE BANQUE :</B> 2301311711585263 <BR><B>DATE VALEUR :</B> 30/01/2023";

        DetailsPrinter dp = new DetailsPrinter();
        String result = dp.format(input);

        Assertions.assertEquals(expectedResult, result);
    }
}
