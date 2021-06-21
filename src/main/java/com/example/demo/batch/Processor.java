/**
 * 
 */
package com.example.demo.batch;

import org.springframework.stereotype.Component;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Vendors;
import com.example.demo.repository.VendorsRepository;

/**
 * @author mohd.hussain
 *
 */

@Component
public class Processor implements ItemProcessor<Vendors, Vendors> {
	
	private static final Logger logger = LoggerFactory.getLogger(Processor.class);

	@Autowired
	private VendorsRepository vendorsRepo;

	@Override
	public Vendors process(Vendors vendor) throws Exception {
		Optional<Vendors> vendorFromDb = vendorsRepo.findById(vendor.getId());
		if (vendorFromDb.isPresent()) {
			logger.debug("vendor already exists {} {}");
		}
		return vendor;
	}
}
