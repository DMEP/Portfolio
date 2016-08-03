<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Contact.aspx.cs" Inherits="hlv1.Contact" %>

<asp:Content runat="server" ID="Content1" ContentPlaceHolderID="MainContent">
    <%-- Page Content --%>
    <%--Banner Container--%>
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <img src="Images/Logos/HLLogoBlack.png">
                <h1 class="h1">
                    <small>Feel free to contact us</small></h1>
                <asp:PlaceHolder runat="server" ID="ErrorMessage" Visible="false">
                    <p class="text-danger">
                        <asp:Literal runat="server" ID="FailureText" />
                    </p>
                </asp:PlaceHolder>
            </div>
        </div>
    </div>
    <%--Contact Form--%>
    <div class="row">
        <div class="col-md-8">
            <section id="contactForm">
                <%--Name--%>
                <div class="form-group">
                    <asp:Label runat="server" CssClass="control-label"><b>Your Name: </b></asp:Label>
                    <asp:TextBox runat="server" ID="YourName" CssClass="form-control" />
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator11" runat="server" ErrorMessage="*" ControlToValidate="YourName" ValidationGroup="save" />
                    <%--Email--%>
                    <div class="form-group">
                        <asp:Label runat="server" CssClass="control-label"><b>Your Email Address: </b></asp:Label>
                        <asp:TextBox runat="server" ID="YourEmail" CssClass="form-control" />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="*"
                            ControlToValidate="YourEmail" ValidationGroup="save" />
                        <asp:RegularExpressionValidator runat="server" ID="RegularExpressionValidator23"
                            SetFocusOnError="true" Text="Example: username@gmail.com" ControlToValidate="YourEmail"
                            ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" Display="Dynamic"
                            ValidationGroup="save" />
                    </div>
                    <%--Subject--%>
                    <div class="form-group">
                        <asp:Label runat="server" CssClass="control-label"><b>Subject: </b></asp:Label>
                        <asp:TextBox runat="server" ID="YourSubject" CssClass="form-control" />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="*"
                            ControlToValidate="YourSubject" ValidationGroup="save" /><br />
                    </div>
                    <%--Message--%>
                    <div class="form-group">
                        <asp:Label runat="server" CssClass="control-label"><b>Your Message: </b></asp:Label>
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ErrorMessage="*" ControlToValidate="Comments" ValidationGroup="save" /><br />
                        <asp:TextBox ID="Comments" runat="server" TextMode="MultiLine" Rows="10" Width="400px" CssClass="form-control" />
                    </div>
                </div>
                <%--Send Button--%>
                <p>
                    <asp:Button runat="server" OnClick="btnContact" Text="Send Message" CssClass="btn btn-default" />
                    <asp:Label ID="DisplayMessage" runat="server" Visible="false" />
                </p>
            </section>
        </div>
        <%-- Address and other contact information --%>
        <div class="col-md-4">
            <legend>Our Store</legend>
            <address>
                <strong>Highs & Lows.</strong><br>
                16 Tariff Street<br>
                Manchester, M1 9FA<br>
                <abbr title="Phone">
                    P:</abbr>
                (0151) 353-6749
            </address>
        </div>
    </div>
</asp:Content>
