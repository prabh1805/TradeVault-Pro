import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Dashboard() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900">
      {/* Navbar */}
      <nav className="bg-slate-800 border-b border-slate-700">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center h-16">
            <div>
              <h1 className="text-2xl font-bold text-white">TradeVault Pro</h1>
            </div>
            <div className="flex items-center gap-4">
              <span className="text-slate-300">Welcome, <span className="font-semibold text-blue-400">{user?.userName}</span></span>
              <button
                onClick={handleLogout}
                className="bg-red-600 hover:bg-red-700 text-white font-semibold py-2 px-4 rounded-lg transition"
              >
                Logout
              </button>
            </div>
          </div>
        </div>
      </nav>

      {/* Main Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          {/* Card 1 */}
          <div className="bg-slate-800 rounded-lg p-6 border border-slate-700 hover:border-blue-500 transition">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-slate-400 text-sm">Portfolio Value</p>
                <p className="text-3xl font-bold text-white mt-2">₹0.00</p>
              </div>
              <div className="text-4xl text-blue-500">📈</div>
            </div>
          </div>

          {/* Card 2 */}
          <div className="bg-slate-800 rounded-lg p-6 border border-slate-700 hover:border-green-500 transition">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-slate-400 text-sm">Total Stocks</p>
                <p className="text-3xl font-bold text-white mt-2">0</p>
              </div>
              <div className="text-4xl text-green-500">📊</div>
            </div>
          </div>

          {/* Card 3 */}
          <div className="bg-slate-800 rounded-lg p-6 border border-slate-700 hover:border-purple-500 transition">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-slate-400 text-sm">Crypto Holdings</p>
                <p className="text-3xl font-bold text-white mt-2">0</p>
              </div>
              <div className="text-4xl text-purple-500">₿</div>
            </div>
          </div>
        </div>

        {/* Welcome Section */}
        <div className="bg-slate-800 rounded-lg p-8 border border-slate-700">
          <h2 className="text-2xl font-bold text-white mb-4">Welcome to TradeVault Pro</h2>
          <p className="text-slate-300 mb-4">
            You're successfully logged in! This is your dashboard where you can manage your stock and cryptocurrency portfolio.
          </p>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-6">
            <div className="bg-slate-700/50 rounded p-4">
              <h3 className="text-blue-400 font-semibold mb-2">📈 Stock Management</h3>
              <p className="text-slate-400 text-sm">Track and manage your stock investments with real-time data.</p>
            </div>
            <div className="bg-slate-700/50 rounded p-4">
              <h3 className="text-purple-400 font-semibold mb-2">₿ Crypto Trading</h3>
              <p className="text-slate-400 text-sm">Monitor your cryptocurrency portfolio and execute trades.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
