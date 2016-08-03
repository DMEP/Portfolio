<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="AdminHome.aspx.cs" Inherits="hlv1.Admin.AdminHome" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h1>Administration</h1>
    <hr />
    <%--Manage Database--%>
    <table>
        <%--Manage Products--%>
        <tr>
            <td>
                <%--Add New Product--%>
                <asp:LinkButton ID="LinkButton1" runat="server" CssClass="button" PostBackUrl="~/Admin/ManageProduct.aspx">Add New Product</asp:LinkButton>
            </td>
        </tr>
        <tr>
            <td>
                <%--Create table of database products--%>
                <%--Allow editing and deletion of products--%>
                <asp:GridView ID="grdProducts" runat="server" AutoGenerateColumns="False" DataKeyNames="ProductID" DataSourceID="sdsProduct" Width="100%" AllowPaging="True" AllowSorting="True" OnRowEditing="grdProducts_RowEditing" CellPadding="4"
                    CssClass="table table-striped table-bordered"
                    GridLines="Vertical" Style="text-align: center">
                    <Columns>
                        <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
                        <asp:BoundField DataField="ProductID" HeaderText="ProductID" InsertVisible="False" ReadOnly="True" SortExpression="ProductID" />
                        <asp:BoundField DataField="ProductName" HeaderText="ProductName" SortExpression="ProductName" />
                        <asp:BoundField DataField="Description" HeaderText="Description" SortExpression="Description" />
                        <asp:BoundField DataField="ImagePath" HeaderText="ImagePath" SortExpression="ImagePath" />
                        <asp:BoundField DataField="UnitPrice" HeaderText="UnitPrice" SortExpression="UnitPrice" />
                        <asp:BoundField DataField="CategoryID" HeaderText="CategoryID" SortExpression="CategoryID" />
                    </Columns>
                </asp:GridView>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">
                <%--insertion/deletion datatypes--%>
                <asp:SqlDataSource ID="sdsProduct" runat="server" ConnectionString="<%$ ConnectionStrings:hlv1 %>" DeleteCommand="DELETE FROM [Products] WHERE [ProductID] = @ProductID" InsertCommand="INSERT INTO [Products] ([ProductName], [Description], [ImagePath], [UnitPrice], [CategoryID]) VALUES (@ProductName, @Description, @ImagePath, @UnitPrice, @CategoryID)" SelectCommand="SELECT * FROM [Products]" UpdateCommand="UPDATE [Products] SET [ProductName] = @ProductName, [Description] = @Description, [ImagePath] = @ImagePath, [UnitPrice] = @UnitPrice, [CategoryID] = @CategoryID WHERE [ProductID] = @ProductID">
                    <DeleteParameters>
                        <asp:Parameter Name="ProductID" Type="Int32" />
                    </DeleteParameters>
                    <InsertParameters>
                        <asp:Parameter Name="ProductName" Type="String" />
                        <asp:Parameter Name="Description" Type="String" />
                        <asp:Parameter Name="ImagePath" Type="String" />
                        <asp:Parameter Name="UnitPrice" Type="Double" />
                        <asp:Parameter Name="CategoryID" Type="Int32" />
                    </InsertParameters>
                    <UpdateParameters>
                        <asp:Parameter Name="ProductName" Type="String" />
                        <asp:Parameter Name="Description" Type="String" />
                        <asp:Parameter Name="ImagePath" Type="String" />
                        <asp:Parameter Name="UnitPrice" Type="Double" />
                        <asp:Parameter Name="CategoryID" Type="Int32" />
                        <asp:Parameter Name="ProductID" Type="Int32" />
                    </UpdateParameters>
                </asp:SqlDataSource>
                <br />
            </td>
        </tr>
        <%--Manage Categories--%>
        <tr>
            <td>
                <%--Add New Category--%>
                <asp:LinkButton ID="LinkButton2" runat="server" CssClass="button" PostBackUrl="~/Admin/ManageCategory.aspx">Add New Category</asp:LinkButton>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">
                <%--Create table of database categories--%>
                <%--Allow editing and deletion of categories--%>
                <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" DataSourceID="sdsProductTypes" Width="50%" AllowPaging="True" AllowSorting="True" DataKeyNames="CategoryID" OnRowEditing="GridView2_RowEditing" CellPadding="4"
                    CssClass="table table-striped table-bordered"
                    GridLines="Vertical">
                    <Columns>
                        <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
                        <asp:BoundField DataField="CategoryID" HeaderText="CategoryID" InsertVisible="False" ReadOnly="True" SortExpression="CategoryID" />
                        <asp:BoundField DataField="CategoryName" HeaderText="CategoryName" SortExpression="CategoryName" />
                        <asp:BoundField DataField="Description" HeaderText="Description" SortExpression="Description" />
                        <asp:BoundField DataField="CatImagePath" HeaderText="CatImagePath" SortExpression="CatImagePath" />
                    </Columns>
                </asp:GridView>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">
                <%--insertion/deletion datatypes--%>
                <asp:SqlDataSource ID="sdsProductTypes" runat="server" ConnectionString="<%$ ConnectionStrings:hlv1 %>" SelectCommand="SELECT [CategoryID], [CategoryName], [Description], [CatImagePath] FROM [Categories]" DeleteCommand="DELETE FROM [Categories] WHERE [CategoryID] = @CategoryID" InsertCommand="INSERT INTO [Categories] ([CategoryName], [Description], [CatImagePath]) VALUES (@CategoryName, @Description, @CatImagePath)" UpdateCommand="UPDATE [Categories] SET [CategoryName] = @CategoryName, [Description] = @Description, [CatImagePath] = @CatImagePath WHERE [CategoryID] = @CategoryID">
                    <DeleteParameters>
                        <asp:Parameter Name="CategoryID" Type="Int32" />
                    </DeleteParameters>
                    <InsertParameters>
                        <asp:Parameter Name="CategoryName" Type="String" />
                        <asp:Parameter Name="Description" Type="String" />
                        <asp:Parameter Name="CatImagePath" Type="String" />
                    </InsertParameters>
                    <UpdateParameters>
                        <asp:Parameter Name="CategoryName" Type="String" />
                        <asp:Parameter Name="Description" Type="String" />
                        <asp:Parameter Name="CatImagePath" Type="String" />
                        <asp:Parameter Name="CategoryID" Type="Int32" />
                    </UpdateParameters>
                </asp:SqlDataSource>
            </td>
        </tr>
    </table>
</asp:Content>
