package libraryMgmt;

import java.util.Scanner;

public class Admin {
	
	public static void main(String[] args) throws Exception{
		int x=0;
		while(x==0) {
			try {
		
		Scanner sc=new Scanner(System.in);
		FunctionPerform f1=new FunctionPerform();
		System.out.println("pls select:\n 1.Register\n 2.login");
		
		int selection=sc.nextInt();
		
		switch(selection) {
		case 1:
			System.out.println("Welcome,Please Register yourself");
			
			sc.nextLine();
			
			System.out.println("Enter username");
			String username=sc.nextLine();
			
			System.out.println("Enter your password");
		    String password=sc.nextLine();
		    
		   boolean done=f1.register(username, password);
		   
		   if(done==true) {
			   System.out.println("you are registered now pls login");
			   break;
		   }else {
			   System.out.println("you are already registered..pls login or enter new username and password");
			   break;
		   }
		case 2:
			System.out.println("pls enter your registerd username and password to login");
			System.out.println();
			sc.nextLine();
			System.out.println("Enter your login username");
			String username1=sc.nextLine();
			
			System.out.println("Enter your login password");
			String password1=sc.nextLine();
			
			boolean login1=f1.login(username1, password1);
			if(login1==true) {
				System.out.println("please select:\n 1.Add\n 2.Delete\n 3.Update\n 4.Books Available\n 5.search book\n 6.Exit");
				int option=sc.nextInt();
				switch(option) {
				case 1:
					System.out.println("please enter book details\n---------");
					sc.nextLine();
					System.out.println("Name:");
					String bname=sc.nextLine();
					
					System.out.println("Author:");
					String author=sc.nextLine();
					
					System.out.println("publication:");
					String publication=sc.nextLine();
					
					System.out.println("cost:");
					double cost=sc.nextDouble();
					
					System.out.println("Edition:");
					int edition=sc.nextInt();
					f1.addBook(bname,author,publication,cost,edition);
					break;
				case 2:
					System.out.println("enter bid to delete particalar book");
					String bid=sc.nextLine();
					f1.deletebook(bid);
					break;
				case 3:
					System.out.println("Enter book id to be update");
					String bid1=sc.nextLine();
				bid=bid1;
				System.out.println("enter new book name");
				String bname1=sc.nextLine();
				bname=bname1;
				System.out.println("Enter new author");
				String author1=sc.nextLine();
				author=author1;
				System.out.println("Enter publication:");
				String publication1=sc.nextLine();
				publication=publication1;
				
				System.out.println("Enter updated cost");
				double cost1=sc.nextDouble();
				cost=cost1;
				System.out.println("enter updated edition");
				int edition1=sc.nextInt();
				edition=edition1;
				f1.updateBook(bid,bname,author,publication,cost,edition);
				break;
				case 4:
					System.out.println("List of all book");
					System.out.println("Bid\tBname\t\t\t\tAuthor\t\t\t\tpublication\t\tcost\t\t\tedition \n---");
					f1.allBooks();
					System.out.println("\n");
					break;
				case 5:
					try {
						System.out.println("press :\n1 to search by Bid\n2.to search by book name\n3.search by author\n4.search by publication\n5.search by cost\n6.search by edition\n7.exit");
						int select=sc.nextInt();
						sc.nextLine();
						switch(select) {
						case 1:
							System.out.println("Enter book ID to be searched");
							bid=sc.nextLine();
							f1.getBookByBid(bid);
							break;
						case 2:
							System.out.println("enter book name to be searched");
							bname=sc.nextLine();
							f1.getBookByName(bname);
							break;
						case 3:
							System.out.println("enter author to be searched");
							author=sc.nextLine();
							f1.getBookByAuthor(author);
							break;
						case 4:
							System.out.println("enter publication to be searched");
							publication=sc.nextLine();
							f1.getBookByPublication(publication);
							break;
						case 5:
							System.out.println("enter book cost to be searched");
							cost=sc.nextDouble();
							f1.getBookBycost(cost);
							break;
						case 6:
							System.out.println("Enter edition to be serched");
							edition=sc.nextInt();
							f1.getBookByedition(edition);
							break;
						case 7:
							System.out.println("Thanks for using library");
							System.out.println("-=---*****-----***---***");
							System.exit(1);
							
						}
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println("Incorrect book data entered...try again");
						e.printStackTrace();
					}
				case 6:
					System.out.println("Thanks for using the library");
					System.out.println("---****----****------******----");
					System.exit(1);
					break;
					default:
						System.out.println("Enter valid choice");
					
				
				}
				
			}
			System.out.println("press:\n1.to continue\n2to exit");
			int res=sc.nextInt();
			if(res==1) {
				System.out.println("you will have to login again");
			}
			else {
				System.out.println("Thanks for using the library");
				System.out.println("---****----****------******----");
				System.exit(1);

			}
		}
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Enter details in the specified format\n Login Again");
				e.printStackTrace();
			}
		}
	}
}
				
			
		
		
		
		
			

