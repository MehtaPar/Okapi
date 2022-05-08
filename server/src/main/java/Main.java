import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.*;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        //This is required to allow GET and POST requests with the header 'content-type'
        options("/*",
                (request, response) -> {
                        response.header("Access-Control-Allow-Headers",
                                "content-type");

                        response.header("Access-Control-Allow-Methods",
                                "GET, POST");


                    return "OK";
                });

        //This is required to allow the React app to communicate with this API
        before((request, response) -> response.header("Access-Control-Allow-Origin", "http://localhost:3000"));

        //TODO: Return JSON containing the candies for which the stock is less than 25% of it's capacity
        get("/low-stock", (request, response) -> {
            JSONArray array = new JSONArray(getLowStock());
            return array;
        });

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            return null;
        });



   }
  //  public class InventoryAlert {
        public static String getLowStock() throws IOException {
            //ArrayList<Integer> candy = new ArrayList<>();
                LinkedList<candy> candyInventory = new LinkedList();


                //importing the inventory workbook
                FileInputStream file = new FileInputStream(new File("../resources/Inventory.xlsx"));
                //Creating the workbook instance with reference of the inventory.xlsx
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                // Getting the first sheet from the workbook
                XSSFSheet inventory = workbook.getSheetAt(1);

                //put method call here
            Iterator<Row> rowIterator = inventory.iterator();       //build list
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                int index = 1;
                candy can = new candy();
                while(cellIterator.hasNext() && index <= 4){
                    Cell cell = cellIterator.next();
                    switch(index){
                        case 1: can.name = cell.getStringCellValue();
                            break;
                        case 2: can.inStock = (int)(cell.getNumericCellValue());
                            break;
                        case 3: can.capacity = (int)(cell.getNumericCellValue());
                            break;
                        case 4: can.id = (int)(cell.getNumericCellValue());
                            break;
                    }
                    index++;
                }
                candyInventory.add(can);
            }
            file.close();

            String output = "[";

            //instantiate json object/list or string
            for(int i = 0; i < candyInventory.size(); i++){            //use list
                if((double)(candyInventory.get(i).inStock/candyInventory.get(i).capacity) < 0.25){
                    System.out.println(candyInventory.get(i).name); //add to json object or string
                    output = output + "{\"" + candyInventory.get(i).name + "\"},";
                }
            }
            output = output.substring(0,output.length()-2);
            output = output + "]";

            //JSONArray array = new JSONArray(output);
            //if string then convert string to json

                return output;
        }
   // }

    public static class candy {
            String name;
            int inStock;
            int id;
            int capacity;

        public candy(){
            name = "";
            inStock = 0;
            id = 0;
            capacity = 0;
        }
            public candy(String n, int IS, int ID, int cap){
                name = n;
                inStock = IS;
                id = ID;
                capacity = cap;
            }
    }

}

/*
            Iterator<Row> rowIterator = sheet.iterator();       //build list
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                int index = 1;
                candy can = new candy();
                while(cellIterator.hasNext() && index <= 4){
                    Cell cell = cellIterator.next();
                    switch(index){
                        case 1: can.name = cell.getStringCellValue();
                                break;
                        case 2: can.inStock = (int)(cell.getNumericCellValue());
                                break;
                        case 3: can.cap = cell.getNumericCellValue();
                                break;
                        case 4: can.id = cell.getNumericCellValue();
                                break;
                    }
                    index++;
                }
                candyInventory.add(can);
             }
             file.close();

                //instantiate json object/list or string
             for(int i = 0; i < candyInventory.size(); i++){            //use list
                if((double)(candyInventory(i).inStock/candyInventory(i).cap) < 0.25){
                    system.out.println(candyInventory(i).name); //add to json object or string
                }
            }
            //if string then convert string to json
 */
//name, stock, cap, id