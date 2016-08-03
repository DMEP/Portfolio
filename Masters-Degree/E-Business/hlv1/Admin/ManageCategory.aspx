<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ManageCategory.aspx.cs" Inherits="hlv1.Admin.ManageCategory" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <%--Shared form between add and edit--%>
    <%--Manage Category Form--%>
    <table>
        <tr>
            <td>
                <asp:Label ID="LabelAddName" runat="server">Name:</asp:Label>
            </td>
            <td>
                <asp:TextBox ID="AddCategoryName" runat="server" CssClass="form-control"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" Text="* Category name required." ControlToValidate="AddCategoryName" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddDescription" runat="server">Description:</asp:Label>
            </td>
            <td>
                <asp:TextBox ID="AddCategoryDescription" runat="server" CssClass="form-control"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" Text="* Description required." ControlToValidate="AddCategoryDescription" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelAddImageFile" runat="server" >Image File:</asp:Label>
            </td>
            <td>
                <asp:FileUpload ID="CategoryImage" runat="server" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" Text="* Image path required." ControlToValidate="CategoryImage" SetFocusOnError="true" Display="Dynamic"></asp:RequiredFieldValidator>
            </td>
        </tr>
        
        <tr>
            <td>
                <asp:Button ID="EditCategoryButton" runat="server" Text="Submit Category" OnClick="EditCategoryButton_Click" CausesValidation="true" CssClass="btn btn-default" />
                
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="LabelEditStatus" runat="server" Text=""></asp:Label>
            </td>
        </tr>
    </table>
</asp:Content>
