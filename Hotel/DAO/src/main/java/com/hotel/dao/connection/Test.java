package com.hotel.dao.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hotel.api.been.IGuest;
import com.hotel.been.Guest;
import com.hotel.been.History;
import com.hotel.been.Option;
import com.hotel.been.Room;

public class Test {
	
	public static void main(String[] args) throws Exception {
//        IGuest guest = getUser(1);
//        System.out.println(guest);
//        HibernateUtil.getSessionFactory().close();
		Session session = HibernateUtil.getSessionFactory()
                                                   .openSession();
		for(int i=1;i<5;i++) {
	//	Guest guest = (Guest)session.get(Guest.class, i);
	//		Option guest = (Option)session.get(Option.class, i);
	//		History guest = (History)session.get(History.class, i);	
			Room guest = (Room)session.get(Room.class, i);
		System.out.println(guest);
		}session.close();
    }
     
//    public static IGuest getUser(Integer userId) throws  Exception {
//        Session session = HibernateUtil.getSessionFactory()
//                                                .openSession();
//        session.beginTransaction();
//        IGuest user = (IGuest) session.get(IGuest.class, userId);
//        session.getTransaction().commit();
//        return user;
//    }

}
