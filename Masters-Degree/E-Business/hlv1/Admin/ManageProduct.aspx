<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ManageProduct.aspx.cs" Inherits="hlv1.Admin.ManageProduct" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%--Shared form between add and edit--%>
    <%--Manage Category Form--%>
    <table>
        <tr>
            <td>
                <asp:Label ID="LabelAddCategory" runat="server">Category:</asp:Label>
            </td>
            <td>
                <asp:DropDownList ID="DropDownAddCategory" runat="server" ItemType="hlv1.Models.Category" SelectMethod="GetCategories" DataTextField="CategoryName" DataValueField="CategoryID"></asp:DropDownList>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddName" runat="server">Name:</asp:Label>
            </td>
            <td>
                <asp:TextBox ID="AddProductName" runat="server" CssClass="form-control"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" Text="* Product name required." ControlToValidate="AddProductName" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddDescription" runat="server">Description:</asp:Label>
            </td>
            <td>
                <asp:TextBox ID="AddProductDescription" runat="server" CssClass="form-control"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" Text="* Description required." ControlToValidate="AddProductDescription" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddPrice" runat="server">Price:</asp:Label>
            </td>
            <td>
                <asp:TextBox ID="AddProductPrice" runat="server" CssClass="form-control"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" Text="* Price required." ControlToValidate="AddProductPrice" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" Text="* Must be a valid price without £." ControlToValidate="AddProductPrice" SetFocusOnError="True" Display="Dynamic" ValidationExpression="^[0-9]*(\.)?[0-9]?[0-9]?$"></asp:RegularExpressionValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddImageFile" runat="server">Image File:</asp:Label>
            </td>
            <td>
                <asp:FileUpload ID="ProductImage" runat="server" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" Text="* New Image path required." ControlToValidate="ProductImage" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Button ID="EditButton" runat="server" Text="Submit Product" OnClick="EditButton_Click" CausesValidation="true" CssClass="btn btn-default" />
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelEditStatus" runat="server" Text=""></asp:Label>
            </td>
        </tr>
    </table>
</asp:Content>