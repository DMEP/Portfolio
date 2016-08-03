<%@ Page Title="Products" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductList.aspx.cs" Inherits="hlv1.ProductList" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%--  Page Content --%>
    <section>
        <div>
            <asp:ListView ID="productList" runat="server"
                DataKeyNames="ProductID" GroupItemCount="4"
                ItemType="hlv1.Models.Product" SelectMethod="GetProducts">
                <EmptyDataTemplate>
                    <table>
                        <tr>
                            <td>No data was returned.</td>
                        </tr>
                    </table>
                </EmptyDataTemplate>
                <EmptyItemTemplate>
                    <td />
                </EmptyItemTemplate>
                <GroupTemplate>
                    <tr id="itemPlaceholderContainer" runat="server">
                        <td id="itemPlaceholder" runat="server"></td>
                    </tr>
                </GroupTemplate>
                <ItemTemplate>
                    <td runat="server">
                        <%--Fill table with products information from database--%>
                        <table>
                            <tr>
                                <td style="padding-top: 50px">
                                    <a href="ProductDetails.aspx?productID=<%#:Item.ProductID%>">
                                        <img src="/Images/Thumbs/<%#:Item.ImagePath%>" style="width: 100px; height: 100px" /></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100px; height: 50px; text-align: center;">
                                    <a href="ProductDetails.aspx?productID=<%#:Item.ProductID%>">
                                        <span>
                                            <b><%#:Item.ProductName%></b>
                                        </span>
                                    </a>
                                    <br />
                                    <span>
                                        <b>Price: </b><%#:String.Format("{0:c}", Item.UnitPrice)%>
                                    </span>
                                    <br />
                                    <a href="AddToCart.aspx?productID=<%#:Item.ProductID %>"><span class="ProductListItem"><b>Add To Cart<b></span></a>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                        </p>
                    </td>
                </ItemTemplate>
                <LayoutTemplate>
                    <table style="width: 100%">
                        <tbody>
                            <tr>
                                <td style="padding-right: 70px">
                                    <%-- Side Container - Categories --%>
                                    <%--Secondary Navigation--%>
                                    <div class="container cat-List text-left">
                                        <h3>
                                            <p>Categories</p>
                                        </h3>
                                        <asp:ListView ID="ListView1" ItemType="hlv1.Models.Category"
                                            runat="server" SelectMethod="GetCategories">
                                            <ItemTemplate>
                                                <b>
                                                    <a href="ProductList.aspx?id=<%#: Item.CategoryID %>"><%#: Item.CategoryName %></a>
                                                </b>
                                            </ItemTemplate>
                                            <ItemSeparatorTemplate>
                                                <br />
                                            </ItemSeparatorTemplate>
                                        </asp:ListView>
                                    </div>
                                    <table id="groupPlaceholderContainer" runat="server" style="width: 70%">
                                        <tr id="groupPlaceholder"></tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                            </tr>
                            <tr></tr>
                        </tbody>
                    </table>
                </LayoutTemplate>
            </asp:ListView>
        </div>
    </section>
</asp:Content>
