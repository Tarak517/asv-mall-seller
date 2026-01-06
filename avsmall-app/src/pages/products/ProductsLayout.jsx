import React from "react";
import { Outlet } from "react-router-dom";

export default function ProductsLayout() {
  return <Outlet />; // This is where all child pages will render
}
