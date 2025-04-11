import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000 // Thay đổi port ở đây
    ,open: true, // Tự động mở trình duyệt khi chạy `vite`

  }
});
