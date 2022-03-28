package com.example.javase13;

@SuppressWarnings("unused")
public class StudyTextBlock {

	public static void main(String[] args) {
		var employeeAsJson = """
			{\n
		       "name": "jack bauer",\n
		       "identity": "11111111110",
		       "salary": 100000,
		       "iban": "tr1"\n	
			}	
	     """;
        System.out.println(employeeAsJson);
        var selectEmployee = """
            select * from employees e
        	where e.salary > ?1	
        		""";
	}

}
