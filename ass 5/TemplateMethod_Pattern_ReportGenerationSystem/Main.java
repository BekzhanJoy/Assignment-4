abstract class ReportGenerator {
    public final void generateReport() {
        formatHeader();
        formatBody();
        formatFooter();
        System.out.println("Report generated successfully.\n");
    }

    protected abstract void formatHeader();
    protected abstract void formatBody();
    protected abstract void formatFooter();
}


class PDFReportGenerator extends ReportGenerator {
    @Override
    protected void formatHeader() {
        System.out.println("Formatting PDF Header");
    }

    @Override
    protected void formatBody() {
        System.out.println("Formatting PDF Body");
    }

    @Override
    protected void formatFooter() {
        System.out.println("Formatting PDF Footer");
    }
}

class HTMLReportGenerator extends ReportGenerator {
    @Override
    protected void formatHeader() {
        System.out.println("Formatting HTML Header");
    }

    @Override
    protected void formatBody() {
        System.out.println("Formatting HTML Body");
    }

    @Override
    protected void formatFooter() {
        System.out.println("Formatting HTML Footer");
    }
}

public class Main {
    public static void main(String[] args) {
        ReportGenerator pdfReport = new PDFReportGenerator();
        ReportGenerator htmlReport = new HTMLReportGenerator();

        System.out.println("Generating PDF Report:");
        pdfReport.generateReport();

        System.out.println("Generating HTML Report:");
        htmlReport.generateReport();
    }
}
