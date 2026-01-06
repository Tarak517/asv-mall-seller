import { Routes, Route, Navigate } from "react-router-dom";
import AdminLayout from "./components/AdminLayout";

/* ================= DASHBOARD ================= */
import SellerDashboard from "./pages/dashboard/SellerDashboard";

/* ================= AUTH ================= */
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import OtpVerification from "./pages/auth/OtpVerification";
import ResetPassword from "./pages/auth/ResetPassword";

/* ================= PRODUCTS ================= */
import ProductsLayout from "./pages/products/ProductsLayout";
import ViewAllProducts from "./pages/products/ViewAllProducts";
import AddNewProducts from "./pages/products/AddNewProducts";
import EditProduct from "./pages/products/EditProduct";
import ProductPreview from "./pages/products/ProductPreview";
import UploadMedia from "./pages/products/UploadMedia";
import Inventory from "./pages/products/Inventory";
import BulkUploadProducts from "./pages/products/BulkUploadProducts";
import InactiveProducts from "./pages/products/InactiveProducts";

/* ================= OVERVIEW ================= */
import Overview from "./pages/overview/Overview";

/* ================= ORDERS ================= */
import Orders from "./pages/orders/Orders";
import OrderDetails from "./pages/orders/OrderDetails";
import OrderStatusUpdate from "./pages/orders/OrderStatusUpdat";
import PickupRequest from "./pages/orders/PickupRequest";
import Invoice from "./pages/orders/Invoice";

/* ================= PAYMENTS ================= */
import PaymentsLayout from "./pages/payments/PaymentsLayout";
import EarningsOverview from "./pages/payments/EarningsOverview";
import TransactionHistory from "./pages/payments/TransactionHistory";

/* ================= SETTINGS / PROFILE ================= */
import SellerProfile from "./pages/profile/SellerProfile";
import EditProfile from "./pages/profile/EditProfile";
import PrivacyTerms from "./pages/profile/PrivacyTerms";
import HelpCenter from "./pages/profile/HelpCenter";

/* ================= NOTIFICATIONS ================= */
import Notifications from "./pages/notifications/Notifications";
/* ================= ANALYTICS ================= */
import AnalyticsLayout from "./pages/analytics/AnalyticsLayout";
import SalesAnalytics from "./pages/analytics/SalesAnalytics";
import InventoryReports from "./pages/analytics/InventoryReports";
import OrderAnalytics from "./pages/analytics/OrderAnalytics";


function App() {
  return (
    <Routes>
      {/* ================= AUTH ================= */}
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/otp-verification" element={<OtpVerification />} />
      <Route path="/reset-password" element={<ResetPassword />} />

      {/* ================= ADMIN ================= */}
      <Route path="/" element={<AdminLayout />}>

        {/* DEFAULT LANDING */}
       <Route path="/" element={<Navigate to="/login" replace />} />


        {/* DASHBOARD */}
        <Route path="dashboard" element={<SellerDashboard />} />

        {/* OVERVIEW */}
        <Route path="overview" element={<Overview />} />

        {/* PRODUCTS */}
        <Route path="products" element={<ProductsLayout />}>
          <Route index element={<ViewAllProducts />} />
          <Route path="add" element={<AddNewProducts />} />
          <Route path="edit/:id" element={<EditProduct />} />
          <Route path="upload-media/:id" element={<UploadMedia />} />
          <Route path="inventory/:id" element={<Inventory />} />
          <Route path="preview/:id" element={<ProductPreview />} />
          <Route path="bulk-upload/:id" element={<BulkUploadProducts />} />
          <Route path="rejected" element={<InactiveProducts />} />
        </Route>

        {/* ORDERS */}
        <Route path="orders" element={<Orders />} />
        <Route path="orders/:id" element={<OrderDetails />} />
        <Route path="orders/:id/status" element={<OrderStatusUpdate />} />
        <Route path="orders/pickup" element={<PickupRequest />} />

        {/* INVOICE */}
        <Route path="invoice/:orderId" element={<Invoice />} />

        {/* PAYMENTS */}
        <Route path="payments" element={<PaymentsLayout />}>
          <Route index element={<EarningsOverview />} />
          <Route path="overview" element={<EarningsOverview />} />
          <Route path="transactions" element={<TransactionHistory />} />
        </Route>

        {/* SETTINGS */}
        <Route path="settings">
          <Route index element={<SellerProfile />} />
          <Route path="profile" element={<SellerProfile />} />
          <Route path="edit-profile" element={<EditProfile />} />
          <Route path="privacy-terms" element={<PrivacyTerms />} />
          <Route path="help-center" element={<HelpCenter />} />
        </Route>
        <Route path="analytics" element={<AnalyticsLayout />}>
        <Route index element={<SalesAnalytics />} />
        <Route path="sales" element={<SalesAnalytics />} />
           <Route path="inventory" element={<InventoryReports />} />
             <Route path="orders" element={<OrderAnalytics />} />
          </Route>


        {/* NOTIFICATIONS */}
        <Route path="notifications" element={<Notifications />} />

        {/* OTHER */}
        <Route path="analytics" element={<div>Analytics Page</div>} />
        

        {/* FALLBACK */}
        <Route path="*" element={<Navigate to="dashboard" replace />} />

      </Route>
    </Routes>
  );
}

export default App;
