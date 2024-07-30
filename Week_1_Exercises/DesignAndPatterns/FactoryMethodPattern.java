interface Document {
    void open();
    void close();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document...");
    }
    public void close() {
        System.out.println("Closing Word document...");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document...");
    }
    public void close() {
        System.out.println("Closing PDF document...");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document...");
    }
    public void close() {
        System.out.println("Closing Excel document...");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        DocumentFactory wordDocFactory = new WordDocumentFactory();
        Document wordDoc = wordDocFactory.createDocument();
        wordDoc.open();
        wordDoc.close();

        DocumentFactory pdfDocFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfDocFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();

        DocumentFactory excelDocFactory = new ExcelDocumentFactory();
        Document excelDoc = excelDocFactory.createDocument();
        excelDoc.open();
        excelDoc.close();
    }
}
