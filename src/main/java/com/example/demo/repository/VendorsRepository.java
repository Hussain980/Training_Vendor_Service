/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Vendors;

/**
 * @author mohd.hussain
 *
 */

@Repository
public interface VendorsRepository extends JpaRepository<Vendors, Integer> {

	
	List<Vendors> findByItemContaining(String item);

	List<Vendors> findByVendorContaining(String vendor);

	Vendors findByItemAndVendor(String item, String vendor);

}
