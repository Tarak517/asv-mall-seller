import React, { useEffect, useState } from "react";
import Topbar from "../../components/Topbar";

export default function TransactionHistory() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function fetchTransactions() {
      try {
        const res = await fetch("http://localhost:9010/api/payments"); 
        if (!res.ok) throw new Error("Failed to fetch transactions");

        const data = await res.json();
        setTransactions(data);
      } catch (err) {
        console.error(err);
        setError("Unable to load transactions");
      } finally {
        setLoading(false);
      }
    }

    fetchTransactions();
  }, []);

  if (loading) return <div className="pt-16 px-8">Loading transactions...</div>;
  if (error) return <div className="pt-16 px-8 text-red-500">{error}</div>;

  return (
    <>
      <Topbar title="Transaction History" />

      <div className="space-y-4">
        <input
          type="text"
          placeholder="Search transactions..."
          className="border px-4 py-2 rounded-md w-full"
        />

        <div className="bg-white border rounded-xl overflow-hidden">
          <table className="w-full text-sm">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-4 py-3 text-left">Txn #</th>
                <th className="px-4 py-3 text-left">Type</th>
                <th className="px-4 py-3 text-left">Amount</th>
                <th className="px-4 py-3 text-left">Status</th>
                <th className="px-4 py-3 text-left">Date</th>
              </tr>
            </thead>

            <tbody>
              {transactions.map((t, i) => (
                <tr key={i} className="border-t">
                  <td className="px-4 py-2">{t.id}</td>
                  <td className="px-4 py-2">{t.type}</td>
                  <td className="px-4 py-2">₹{t.amount}</td>
                  <td className={`px-4 py-2 font-medium ${
                    t.status === "Settled" ? "text-green-600" : "text-orange-600"
                  }`}>
                    {t.status}
                  </td>
                  <td className="px-4 py-2">{t.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
