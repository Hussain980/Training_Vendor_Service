/**
 * 
 */
package com.example.demo.batch;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.entity.Vendors;
import com.example.demo.repository.VendorsRepository;

/**
 * @author mohd.hussain
 *
 */

@Component
public class Writer implements  ItemWriter<Vendors>{

	@Autowired
	private VendorsRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Vendors> vendors) throws Exception {
		repo.saveAll(vendors);
	}
}
