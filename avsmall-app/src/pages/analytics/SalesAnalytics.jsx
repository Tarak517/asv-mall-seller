const SalesAnalytics = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Total Sales</h3>
        <p className="text-xl font-semibold">₹1,24,000</p>
      </div>

      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Orders</h3>
        <p className="text-xl font-semibold">312</p>
      </div>

      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Conversion Rate</h3>
        <p className="text-xl font-semibold">3.2%</p>
      </div>
    </div>
  );
};

export default SalesAnalytics;
