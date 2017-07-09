package positionaludf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.ArrayList;

@Description(
        name="Positional UDF",
        value="return correct positional data based on unsigned input",
        extended="select evaluate_udf(array) from breadcrumb limit 1"
        )

public class evaluate_udf extends UDF {

    private ArrayList<Integer> element = new ArrayList <>();
    Integer int_output;
    public ArrayList<Integer> evaluate (ArrayList<Integer> value) {

        Integer x = null;
        Integer y = null;
        Integer signed = 32768;
        Integer unsigned = 65536;
        element.clear();
        for (int i = 0; i < value.size(); i++) {

            if (value.get(i) >= signed) {
                x = value.get(i) - unsigned;
                element.add(i,x);
            } else {
                y = value.get(i);
                element.add(i,y);
            }
        }
        return element;
    }
}

