package com.example.world.ui;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.example.world.domain.World;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class StudyJaxbReader {

	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(World.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File file = new File("resources", "countries.xml");
		World world = (World) unmarshaller.unmarshal(file);
		world.getCountries().forEach(System.out::println);
	}

}
