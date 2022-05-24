package libraryMgmt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FunctionPerform  {
	//we use Jdbc here to use 
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
Statement st=null;
ResultSetMetaData rsmt=null;
//first we make method to user login by register
public boolean register(String username,String password)throws SQLException {
	boolean valid=false;
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("insert into libauth values(?,?)");
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		boolean b=ps.execute();
		System.out.println(b);
		rs=ps.getResultSet();
		if(rs.next()) {
			
			System.out.println("you are successfully Registered");
			valid=true;
		}else {
			System.out.println("Maybe username already registered");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return valid;
}
public boolean login(String username,String password) {
	boolean valid=false;
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from libauth where username=? and password=?");
		ps.setString(1, username);
		ps.setString(2, password);
		boolean b=ps.execute();
		rs=ps.getResultSet();
		if(rs.next()) {
			valid=true;
			String name1=rs.getString(1);
			System.out.println("Hello"+" " +name1+" "+ "Welcome to online Library");
		}else
		{
			System.out.println("Enter valid ID and password");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return valid;
}
public int addBook(String bname,String author,String publication,double cost,int edition) {
	int x=0;
	Book bo=new Book(bname,author,publication,cost,edition);
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("insert into librarybooks values(?,?,?,?,?,?)");
		ps.setString(1, bo.getNewBookId());
		/*Sets the designated parameter to the given Java String value
		 and convert this to an sql varchar or Longvarchar when sending to the database
		 */
		System.out.println(bo.getNewBookId());
		ps.setString(2, bo.getBname());
		System.out.println(bo.getBname());
		ps.setString(3,bo.getAuthor());
		System.out.println(bo.getAuthor());
		ps.setString(4,bo.getPublication());
		System.out.println(bo.getPublication());
		ps.setDouble(5, bo.getCost());
		System.out.println(bo.getCost());
		ps.setInt(6, bo.getEdition());
		System.out.println(bo.getEdition());
		//now execute query
		x=ps.executeUpdate();
		if(x==1) {
			System.out.println("your book"+" "+bo.getBname()+" "+"added successfully");
		}else {
			System.out.println("Book not added,please enter valid details");
		}
		
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	return x;
}
//update book
public int updateBook(String bid,String bname,String author,String publication,double cost,int edition) 
{
	Book bo=new Book(bid, bname, author, publication, cost, edition);
	int x=0;
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("update librarybooks SET bname=?,author=?,publication=?,cost=?,edition=? where bid=?");
		ps.setString(1, bo.getBname());
		System.out.println(bo.getBname());
		ps.setString(2, bo.getAuthor());
		System.out.println(bo.getAuthor());
		ps.setString(3, bo.getPublication());
		System.out.println(bo.getPublication());
		ps.setDouble(4, bo.getCost());
		System.out.println(bo.getCost());
		ps.setInt(5, bo.getEdition());
		ps.setString(6, bo.getBid());
		System.out.println(bo.getBid());
	 x=ps.executeUpdate();
	 
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return x;
	
}
//showing all available books of library
public boolean  allBooks() {
	boolean valid=false;
	Book bo=new Book();
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks");
		rs=ps.executeQuery();
		while(rs.next()) {
			valid=true;
			String bid=rs.getString(1);
			String bname=rs.getString(2);
			String author=rs.getString(3);
			String publication=rs.getString(4);
			double cost=rs.getDouble(5);
			int edition=rs.getInt(6);
			System.out.println(bid+"\t"+bname+"\t\t\t"+author+"\t\t\t"+publication+"\t\t\t"+cost+"\t\t\t"+edition);
		
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	return valid;
	
	
}
public Book getBookByBid(String bid) {
	Book bo=new Book();
	int x=0;
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where bid=?");
		ps.setString(1, bid);
		rs=ps.executeQuery();
		if(rs.next()) {
			while(x==0) {
				String Bid=rs.getString(1);
				String bname=rs.getString(2);
				String author=rs.getString(3);
				String publication=rs.getString(4);
				double cost=rs.getDouble(5);
				int edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				System.out.println("result of Seraching..Thank you..Have a nice Day");
				x++;
			}
		}else {
			System.out.println("Enter bid is wrong or Book does not exist");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public Book getBookByName(String bname){
	Book bo=new Book();
	
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where bname=?");
		ps.setString(1, bname);
		rs=ps.executeQuery();
		if(rs.next()) {
			do {
				bo.bid=rs.getString(1);
				bo.bname=rs.getString(2);
				bo.Author=rs.getString(3);
				bo.publication=rs.getString(4);
				bo.cost=rs.getDouble(5);
				bo.edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				
			}while(rs.next());
			System.out.println("above is the list of books with the said name:");
		
		}else {
			System.out.println("book of specified bname not found");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public Book getBookByAuthor(String Author) {
	
Book bo=new Book();
	
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where Author=?");
		ps.setString(1, Author);
		rs=ps.executeQuery();
		if(rs.next()) {
			do {
				bo.bid=rs.getString(1);
				bo.bname=rs.getString(2);
				bo.Author=rs.getString(3);
				bo.publication=rs.getString(4);
				bo.cost=rs.getDouble(5);
				bo.edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				
			}while(rs.next());
			System.out.println("above is the list of books with the said Author:");
		
		}else {
			System.out.println("book of specified Author not found");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public Book getBookByPublication(String publication) {
Book bo=new Book();
	
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where publication=?");
		ps.setString(1, publication);
		rs=ps.executeQuery();
		if(rs.next()) {
			do {
				bo.bid=rs.getString(1);
				bo.bname=rs.getString(2);
				bo.Author=rs.getString(3);
				bo.publication=rs.getString(4);
				bo.cost=rs.getDouble(5);
				bo.edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				
			}while(rs.next());
			System.out.println("above is the list of books with the said publicaion:");
		
		}else {
			System.out.println("book of specified publication not found");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public Book getBookBycost(Double cost) {
Book bo=new Book();
	
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where cost=?");
		ps.setDouble(1, cost);
		rs=ps.executeQuery();
		if(rs.next()) {
			do {
				bo.bid=rs.getString(1);
				bo.bname=rs.getString(2);
				bo.Author=rs.getString(3);
				bo.publication=rs.getString(4);
				bo.cost=rs.getDouble(5);
				bo.edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				
			}while(rs.next());
			System.out.println("above is the list of books with the said cost:");
		
		}else {
			System.out.println("book of specified cost not found");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public Book getBookByedition(int edition) {
Book bo=new Book();
	
	try {
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("select * from librarybooks where edition=?");
		ps.setInt(1, edition);
		rs=ps.executeQuery();
		if(rs.next()) {
			do {
				bo.bid=rs.getString(1);
				bo.bname=rs.getString(2);
				bo.Author=rs.getString(3);
				bo.publication=rs.getString(4);
				bo.cost=rs.getDouble(5);
				bo.edition=rs.getInt(6);
				System.out.println("------------------");
				System.out.println(bo.toString());
				System.out.println("_____________________________________");
				
			}while(rs.next());
			System.out.println("above is the list of books with the said edition:");
		
		}else {
			System.out.println("book of specified edition not found");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return bo;
}
public void deletebook(String bid) {
	try {
		int i=-1;
		con=Connection4Jdbc.getConnection();
		ps=con.prepareStatement("delete from librarybooks where bid=?");
		ps.setString(1, bid);
	i=ps.executeUpdate();
		if(i>=0) {
			System.out.println("your book successfully removed from database");
		}else {
			System.out.println("you enter wrong bid or book is already not present");
		}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}

//lawda 
