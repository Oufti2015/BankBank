package sst.bank.report.html;

public class DetailsPrinter {
    public String format(String detail) {
        String result = detail;
        result = result.replace("COMMUNICATION :", "<BR><B>COMMUNICATION :</B>");
        result = result.replace("NUMERO DE MANDAT :", "<BR><B>NUMERO DE MANDAT :</B>");
        result = result.replace("REFERENCE :", "<BR><B>REFERENCE :</B>");
        result = result.replace("REFERENCE BANQUE :", "<BR><B>REFERENCE BANQUE :</B>");
        result = result.replace("DATE VALEUR :", "<BR><B>DATE VALEUR :</B>");
        result = result.replace("4871 04XX XXXX 6276", "4871 04XX XXXX 6276<BR>");
        return result;
    }
}
