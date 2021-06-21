/**
 * 
 */
package com.example.demo.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Vendors;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.repository.VendorsRepository;
import com.example.demo.service.VendorService;

/**
 * @author mohd.hussain
 *
 */
@Service
public class VendorServiceImpl implements VendorService {

	private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);

	@Autowired
	VendorsRepository vendorRepo;

	@Override
	public List<Vendors> getItemList(String item) {
		logger.debug("Inside getItemList {}  {}  {}");
		List<Vendors> items = vendorRepo.findByItemContaining(item);
		if (items.isEmpty()) {
			throw new ItemNotFoundException("items are not available");
		}
		return items;
	}

	@Override
	public List<Vendors> getItemListByVendor(String vendor) {
		logger.debug("Inside getItemListByVendor   {}  {}  {}");
		List<Vendors> vendors = vendorRepo.findByVendorContaining(vendor);
		if (vendors.isEmpty()) {
			throw new ItemNotFoundException("vendor is not available");
		}
		return vendors;
	}

	@Override
	public Vendors getItems(String item, String vendor) {
		logger.debug("Inside getItems   {}  {}  {}");
		return vendorRepo.findByItemAndVendor(item, vendor);
	}

	@Override
	public Vendors updateQuantity(Vendors vendors) {
		logger.debug("Inside updateQuantity   {}  {}  {}");
		Vendors vendordb = vendorRepo.findByItemAndVendor(vendors.getItem(), vendors.getVendor());
		vendordb.setQuantity(vendors.getQuantity());
		return vendorRepo.save(vendordb);
		
	}

}
