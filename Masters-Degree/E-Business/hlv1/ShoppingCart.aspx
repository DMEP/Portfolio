<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ShoppingCart.aspx.cs" Inherits="hlv1.ShoppingCart" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%-- Page Content --%>
    <div id="ShoppingCartTitle" runat="server" class="ContentHead">
        <h1>Shopping Cart</h1>
    </div>
    <%-- Items Container --%>
    <asp:GridView ID="CartList" runat="server" AutoGenerateColumns="False" CellPadding="4" CssClass="table table-striped table-bordered" GridLines="Vertical" ItemType="hlv1.Models.CartItem"
        SelectMethod="GetShoppingCartItems" ShowFooter="True" Style="width: 100%; float: right">
        <%-- DataField Container --%>
        <%-- Add retrieved product information --%>
        <Columns>
            <asp:BoundField DataField="ProductID" HeaderText="ID" SortExpression="ProductID" />
            <asp:BoundField DataField="Product.ProductName" HeaderText="Name" />
            <asp:BoundField DataField="Product.UnitPrice" HeaderText="Price (each)" DataFormatString="{0:c}" />
            <%-- Add editable quantity --%>
            <asp:TemplateField HeaderText="Quantity">
                <ItemTemplate>
                    <asp:TextBox ID="PurchaseQuantity" Width="40" runat="server" Text="<%#: Item.Quantity %>"></asp:TextBox>
                </ItemTemplate>
            </asp:TemplateField>
            <%--  Add total cost that changes with quantity --%>
            <asp:TemplateField HeaderText="Item Total">
                <ItemTemplate>
                    <%#: String.Format("{0:c}", ((Convert.ToDouble(Item.Quantity)) *  Convert.ToDouble(Item.Product.UnitPrice)))%>
                </ItemTemplate>
            </asp:TemplateField>
            <%-- Add checkbox to remove items --%>
            <asp:TemplateField HeaderText="Remove Item">
                <ItemTemplate>
                    <asp:CheckBox ID="Remove" runat="server"></asp:CheckBox>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
    <%-- Total Cost Container --%>
    <div>
        <p></p>
        <strong>
            <asp:Label ID="LabelTotalText" runat="server" Text="Order Total: "></asp:Label>
            <asp:Label ID="lblTotal" runat="server" EnableViewState="false"></asp:Label>
        </strong>
    </div>
    <br />
    <%-- Buttons Container --%>
    <table>
        <tr>
            <td>
                <%-- Update button when items edited or deleted --%>
                <asp:Button ID="UpdateBtn" runat="server" Text="Update" OnClick="UpdateBtn_Click" CssClass="btn btn-default" />
                <%-- Contine shopping button to return to category page --%>
                <asp:Button ID="Continue" runat="server" Text="Continue Shopping" PostBackUrl="~/CatThumbs.aspx" CssClass="btn btn-default" />
            </td>
            <td>
                <%-- PayPal button to purchase product --%>
                <asp:ImageButton ID="CheckoutImageBtn" runat="server" ImageUrl="http://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" Width="145" AlternateText="Check out with PayPal"
                    OnClick="CheckoutBtn_Click" BackColor="Transparent" BorderWidth="0" />
            </td>
        </tr>
    </table>
</asp:Content>
