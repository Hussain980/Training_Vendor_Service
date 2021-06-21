/**
 * 
 */
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Vendors;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.repository.VendorsRepository;
import com.example.demo.serviceimpl.VendorServiceImpl;

/**
 * @author mohd.hussain
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTest {

	@Mock
	VendorsRepository vendorsRepo;

	@InjectMocks
	VendorServiceImpl vendorServiceImpl;

	static Vendors vendor1;

	static Vendors vendor2;

	static Vendors vendor3;

	@BeforeAll
	public static void setup() {

		vendor1 = new Vendors();
		vendor2 = new Vendors();
		vendor3 = new Vendors();
		vendor1.setItem("tea");
		vendor1.setPrice(12);
		vendor1.setQuantity(2);
		vendor1.setVendor("Vendor A");
		vendor2.setItem("tea");
		vendor2.setPrice(15);
		vendor2.setQuantity(4);
		vendor2.setVendor("Vendor B");
		vendor3.setItem("Coffee");
		vendor3.setPrice(10);
		vendor3.setQuantity(2);
		vendor3.setVendor("Vendor B");

	}

	@Test
	@DisplayName("Find all items : positive scenario")
	public void testGetItemList() {
		List<Vendors> vendorlist = new ArrayList<>();
		vendorlist.add(vendor1);
		vendorlist.add(vendor2);

		when(vendorsRepo.findByItemContaining("tea")).thenReturn(vendorlist);
		List<Vendors> vendors = vendorServiceImpl.getItemList("tea");
		assertEquals(vendorlist, vendors);
	}

	@Test
	@DisplayName("Find all items : negative scenario")
	public void testGetItemList1() {
		List<Vendors> vendorlist = new ArrayList<>();
		vendorlist.add(vendor1);
		vendorlist.add(vendor2);

		when(vendorsRepo.findByItemContaining("tea")).thenThrow(ItemNotFoundException.class);
		assertThrows(ItemNotFoundException.class, () -> vendorServiceImpl.getItemList("tea"));
	}

	@Test
	@DisplayName("Find all items by vendor: positive scenario")
	public void testGetItemListByVendor() {
		List<Vendors> vendorlist = new ArrayList<>();
		vendorlist.add(vendor1);
		vendorlist.add(vendor2);
		vendorlist.add(vendor3);

		when(vendorsRepo.findByVendorContaining("Vendor B")).thenReturn(vendorlist);
		List<Vendors> vendors = vendorServiceImpl.getItemListByVendor("Vendor B");
		assertEquals(vendorlist, vendors);
	}

	@Test
	@DisplayName("Find all items by vendor: negative scenario")
	public void testGetItemListByVendor1() {
		List<Vendors> vendorlist = new ArrayList<>();
		vendorlist.add(vendor1);
		vendorlist.add(vendor2);
		vendorlist.add(vendor3);

		when(vendorsRepo.findByVendorContaining("Vendor B")).thenThrow(ItemNotFoundException.class);
		assertThrows(ItemNotFoundException.class, () -> vendorServiceImpl.getItemListByVendor("Vendor B"));
	}
	
	@Test
	@DisplayName("Update item quantity: positive scenario")
	public void testUpdateQuantity() {
		

		when(vendorsRepo.findByItemAndVendor("tea","Vendor A")).thenReturn(vendor1);
		vendor1.setQuantity(1);
		when(vendorsRepo.save(vendor1)).thenReturn(vendor1);
		Vendors vendors = vendorServiceImpl.updateQuantity(vendor1);
		assertEquals(vendor1, vendors);
	}

}
