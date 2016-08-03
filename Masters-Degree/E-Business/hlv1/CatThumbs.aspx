<%@ Page Title="Products" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="CatThumbs.aspx.cs" Inherits="hlv1.CatThumbs" %>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <section>
        <div>
            <asp:ListView ID="categoryList0" ItemType="hlv1.Models.Category"
                runat="server" SelectMethod="GetCategories" GroupItemCount="4">
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
                <%--Fill table with categories database information--%>
                <ItemTemplate>
                    <td runat="server" style="padding-left: 70px">
                        <table>
                            <tr>
                                <td>
                                    <a href="ProductList.aspx?productID=<%#:Item.CategoryID%>">
                                        <img src="/Images/Thumbs/<%#:Item.CatImagePath %>" style="width: 100px; height: 100px" /></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 100px; height: 50px; text-align: center;">
                                    <a href="ProductList.aspx?id=<%#:Item.CategoryID%>">
                                        <span>
                                            <b><%#: Item.CategoryName %></b>
                                        </span>
                                    </a>
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
                    <table style="width: 100%;">
                        <tbody>
                            <tr>
                                <td style="padding-top: 20px; padding-left: 20px;">
                                    <table id="groupPlaceholderContainer" runat="server" style="width: 70%;">
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
