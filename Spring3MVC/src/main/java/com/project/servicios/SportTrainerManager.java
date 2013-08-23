package com.project.servicios;

import com.project.model.Address;
import com.project.model.ContactInfo;
import com.project.model.SportTrainer;

public class SportTrainerManager {

	public void manageSportTrainer(SportTrainer sp,SportTrainer res){
		if(sp.getName() != null && !sp.getName().equals("")){
			res.setName(sp.getName());
		}
		if(sp.getSurName()!= null && !sp.getSurName().equals("")){
			res.setSurName(sp.getSurName());
		}
		if(res.getContactInfo() == null){
			res.setContactInfo(new ContactInfo());
		}
		if(sp.getContactInfo().getPhoneNumber() != null && !sp.getContactInfo().getPhoneNumber().equals("")){
			res.getContactInfo().setPhoneNumber(sp.getContactInfo().getPhoneNumber());
		}
		if(sp.getContactInfo().getMobileNumber() != null && !sp.getContactInfo().getMobileNumber().equals("")){
			res.getContactInfo().setMobileNumber(sp.getContactInfo().getMobileNumber());
		}
		if(res.getContactInfo().getAddress() == null){
			res.getContactInfo().setAddress(new Address());
		}
		if(sp.getContactInfo().getAddress().getCity() != null && !sp.getContactInfo().getAddress().getCity().equals("")){
			res.getContactInfo().getAddress().setCity(sp.getContactInfo().getAddress().getCity());
		}
		if(sp.getContactInfo().getAddress().getCountry() != null && !sp.getContactInfo().getAddress().getCountry().equals("")){
			res.getContactInfo().getAddress().setCountry(sp.getContactInfo().getAddress().getCountry());
		}
		if(sp.getContactInfo().getAddress().getPostalCode() != null && !sp.getContactInfo().getAddress().getPostalCode().equals("")){
			res.getContactInfo().getAddress().setPostalCode(sp.getContactInfo().getAddress().getPostalCode());
		}
		if(sp.getContactInfo().getAddress().getAddress() != null && !sp.getContactInfo().getAddress().getAddress().equals("")){
			res.getContactInfo().getAddress().setAddress(sp.getContactInfo().getAddress().getAddress());
		}
	}
}
