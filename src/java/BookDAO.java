
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mypkg.Book;

//this is a DAO class
//DAO classes holds the methods for CRUD operations

public class BookDAO {
    
    public Book searchBook(int code){
        Book book=new Book();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdata","root","root");
            String sql="SELECT * FROM BOOKS WHERE bcode=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, code);
            ResultSet rs=ps.executeQuery();
            rs.next();
            book.setCode(rs.getInt(1));
            book.setTitle(rs.getString(2));
            book.setSubject(rs.getString(3));
            book.setAuthor(rs.getString(4));
            book.setPrice(rs.getInt(5));
            con.close();
            return book;
        }catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }
}
