using System.Data.Entity;

namespace hlv1.Models
{
    public class ProductContext : DbContext
    {
        public ProductContext() : base("hlv1")
        {

        }
        public DbSet<Category> Categories { get; set; }
        public DbSet<Product> Products { get; set; }
        public DbSet<CartItem> ShoppingCartItems { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<OrderDetail> OrderDetails { get; set; }
    }
}