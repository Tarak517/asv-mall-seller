import { Outlet } from "react-router-dom";

export default function PaymentsLayout() {
  return (
    <div className="pt-0 px-0">
      <Outlet />
    </div>
  );
}
