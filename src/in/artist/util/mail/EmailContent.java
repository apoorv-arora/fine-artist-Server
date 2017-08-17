package in.artist.util.mail;

import java.util.ArrayList;

import in.artist.util.CommonLib;

public class EmailContent {

//	public static void triggerSupplierSignUpEmail(final Supplier supplier) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				EmailModel eModel = new EmailModel();
//				eModel.setFrom(CommonLib.ZAPP_ID);
//				ArrayList<String> senders = new ArrayList<String>();
//				senders.add(supplier.getEmail());
//				eModel.setSenders(senders);
//				eModel.setSubject("Shift now - Credentials");
//				eModel.setContent("You login credentials are:\n" + "username: " + supplier.getEmail() + "\n"
//						+ "password: " + supplier.getPassword() + "\n");
//
//				Email.getInstance().sendEmail(eModel);
//			}
//		}).start();
//	}
}
