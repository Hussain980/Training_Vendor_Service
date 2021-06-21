/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Vendors;

/**
 * @author mohd.hussa
 *
 */

@Service
public interface VendorService {

	public List<Vendors> getItemList(String item);


	public List<Vendors> getItemListByVendor(String vendor);


	public Vendors getItems(String item, String vendor);


	public Vendors updateQuantity(Vendors vendors);
}
