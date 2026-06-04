import java.util.LinkedList;

class Product {
    private String prodCode;
    private String prodType;
    private String prodDescription;
    private Brand prodBrand;
    private int prodSale;

    public Product(String prodCode, String prodType, String prodDescription, Brand prodBrand, int prodSale) throws Exception{
        this.prodCode = prodCode;
        this.prodType = prodType;
        this.prodDescription = prodDescription;
        this.prodBrand = prodBrand;
        setProdSale(prodSale);
    }

    public int getProdSale(){
        return prodSale;
    }
    public Brand getProdBrand(){
        return prodBrand;
    }
    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdBrand(Brand prodBrand) {
        this.prodBrand = prodBrand;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public void setProdSale(int prodSale) throws Exception {
        if (prodSale < 0)
            throw new Exception("Number of product sales can't be negative! \n");
        this.prodSale = prodSale;
    }
}

class Brand {
    private String brandName;
    private float brandPrice;
    public Brand(String brandName, float brandPrice) {
        this.brandName = brandName;
        this.brandPrice = brandPrice;
    }
    public float getBrandPrice() {
        return brandPrice;
    }
}

class ProductManagement {
    private Product[] allProducts = new Product[25];
    public void sortProducts(){
        int index = 0;
        int n = allProducts.length;
        for (int i = 0; i < n; i++){
            int biggestValue = allProducts[i].getProdSale();
            int biggestValueIndex = i;
            for (int j = i; j < n; j++)
            {
                int value = allProducts[j].getProdSale();
                if (value > biggestValue) {
                    biggestValue = value;
                    biggestValueIndex = j;
                }
            }
            Product temp = allProducts[i];
            allProducts[i] = allProducts[biggestValueIndex];
            allProducts[biggestValueIndex] = temp;
        }
    }
}

class Invoice {
    private String invoiceID;
    private static Product[] products = new Product[20];

    private static int[] prodQuantity = new int[20];

    private boolean qualifiesForDiscount;
    private int numOfProducts;

    public String getInvoiceID(){
        return invoiceID;
    }

    public void addProduct(Product product, int quantity) {
        products[numOfProducts] = product;
        prodQuantity[numOfProducts] = quantity;
        numOfProducts++;
    }
    public void setQualifiesForDiscount(){
        int value = 0;
        for (int i = 0; i < this.numOfProducts; i++){
            value += products[i].getProdSale() * prodQuantity[i];
        }
        if (value > 3000)
            qualifiesForDiscount = true;
    }
}

class Supplier {
    private String supplierName;
    private String supplierCountry;
    private String[] productNames = new String[10];
    public Supplier(String supplierName, String supplierCountry,
                    String[] productNames) {
        this.supplierName = supplierName;
        this.supplierCountry = supplierCountry;
        this.productNames = productNames;
    }
    public String getSupplierName(){
        return supplierName;
    }
    public String getSupplierCountry(){
        return supplierCountry;
    }
    public String[] getProductNames() {
        return productNames;
    }
    public void setProductNames(String[] productNames) {
        this.productNames = productNames;
    }
    public void setSupplierCountry(String supplierCountry) {
        this.supplierCountry = supplierCountry;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void displayData() {
        System.out.println("Supplier: " + supplierName + ", Country: " + supplierCountry);
    }
}

class SupplierManager {
    LinkedList<Supplier> supplierList;
    public SupplierManager() {
        supplierList = new LinkedList<>();
    }
    public void addSupplier(Supplier newSupplier) {
        int index = 0;

        while (index < supplierList.size() && supplierList.get(index).getSupplierName().compareTo(newSupplier.getSupplierName()) < 0) {
            index++;
        }

        supplierList.add(index, newSupplier);
    }
    public void displayList() {
        for (int i = 0; i < supplierList.size(); i++){
            supplierList.get(i).displayData();
        }
    }
    public static int countOfSuppliers(LinkedList<Supplier> supplierList, String country, int n) {
        if (n == 0) {
            return 0;
        }

        if (supplierList.get(n - 1).getSupplierCountry().equals(country)) {
            return 1 + countOfSuppliers(supplierList, country, n - 1);
        } else {
            return countOfSuppliers(supplierList, country, n - 1);
        }
    }
}

public class homework {
    public static void main(String[] args) {
        Brand brand1 = new Brand("Safesun", 2.17f);
    }
}