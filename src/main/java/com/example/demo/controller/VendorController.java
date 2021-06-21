/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Vendors;
import com.example.demo.service.VendorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * @author mohd.hussain
 *
 */
@Api(value = "VendorController", tags = { "Vendor Controller" })
@SwaggerDefinition(tags = {
		@Tag(name = "VendorController Controller", description = "Vendor Controller contains some end points related to Vendor operation") })
@RestController
@Validated
public class VendorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);
	
	@Autowired
	VendorService vendorService;

	@ApiOperation(value = "Search items based on the item name")
	@GetMapping("/searchItems")
	public List<Vendors> getItemList(@RequestParam @NotEmpty(message = "item can not be empty") String item){
		logger.info("inside getItemList method   {} {} {}");
		return vendorService.getItemList(item);
	}
	
	@ApiOperation(value = "Search items based on the vendor name")
	@GetMapping("/searchItemsByVendors")
	public List<Vendors> getItemListByVendor(@RequestParam @NotEmpty(message = "vendor can not be empty") String vendor){
		logger.info("inside getItemListByVendor method   {} {} {}");
		return vendorService.getItemListByVendor(vendor);
	}
	
	@ApiOperation(value = "Search items based on the item name and vendor name")
	@GetMapping("/searchItem")
	public Vendors getItems(@RequestParam String item , @RequestParam String vendor) {
		logger.info("inside getItems method {}    {}");
		return vendorService.getItems(item,vendor);
	}
	
	@ApiOperation(value = "update number of quantity of items")
	@PutMapping("/updateQuantity")
	public Void updateQuantity(@RequestBody Vendors vendors) {
		logger.info("inside updateQuantity method ===  {} " + vendors);
		vendorService.updateQuantity(vendors);
		return null;

	}
}
