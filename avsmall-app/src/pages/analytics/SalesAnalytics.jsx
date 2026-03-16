import React, { useEffect, useState } from "react";

const SalesAnalytics = () => {

  const [analytics, setAnalytics] = useState({
    totalSales: 0,
    orders: 0,
    conversionRate: 0
  });

  useEffect(() => {

    // Fake API response
    const fakeAnalytics = {
      totalSales: 124000,
      orders: 312,
      conversionRate: 3.2
    };

    setTimeout(() => {
      setAnalytics(fakeAnalytics);
    }, 800);

  }, []);

  return (

    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">

      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Total Sales</h3>
        <p className="text-xl font-semibold">
          ₹{analytics.totalSales.toLocaleString()}
        </p>
      </div>

      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Orders</h3>
        <p className="text-xl font-semibold">
          {analytics.orders}
        </p>
      </div>

      <div className="bg-white p-4 border rounded-lg">
        <h3 className="text-sm text-gray-500">Conversion Rate</h3>
        <p className="text-xl font-semibold">
          {analytics.conversionRate}%
        </p>
      </div>

    </div>

  );
};

export default SalesAnalytics;