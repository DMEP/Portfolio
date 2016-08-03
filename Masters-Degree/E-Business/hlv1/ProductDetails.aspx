<%@ Page Title="Product Details" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ProductDetails.aspx.cs" Inherits="hlv1.ProductDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%--Page Content--%>
    <asp:FormView ID="productDetail" runat="server" ItemType="hlv1.Models.Product" SelectMethod="GetProduct" RenderOuterTable="false">
        <ItemTemplate>
            <div>
                <h1><%#:Item.ProductName %></h1>
            </div>
            <br />
            <%--Fill table with product information from database--%>
            <table>
                <tr>
                    <td>
                        <img src="/Images/Thumbs/<%#:Item.ImagePath %>" style="height: 300px; width: 300px" alt="<%#:Item.ProductName %>" />
                    </td>
                    <td>&nbsp;</td>
                    <td style="vertical-align: top; text-align: left; padding-left: 20px;">
                        <b>Description:</b><br />
                        <%#:Item.Description %>
                        <br />
                        <span><b>Price:</b>&nbsp;<%#: String.Format("{0:c}", Item.UnitPrice) %></span>
                        <br />
                        <span><b>Product Number:</b>&nbsp;<%#:Item.ProductID %></span>
                        <br />
                        <asp:Button ID="Continue" runat="server" Text="Continue Shopping" PostBackUrl="~/CatThumbs.aspx" CssClass="btn btn-default" />
                        <a href="AddToCart.aspx?productID=<%#:Item.ProductID %>"><span class="ProductListItem"><b>Add To Cart<b></span> </a>
                    </td>
                </tr>
            </table>
        </ItemTemplate>
    </asp:FormView>
</asp:Content>
