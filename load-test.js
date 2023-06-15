import http from 'k6/http';
import { check, sleep } from 'k6';
export const options = {
  vus: 40,
  duration: '300s',
};

export default function () {
  const res = http.get('http://localhost:8081/v1/characters/1');
  check(res, { 'status was 200': (r) => r.status == 200 });
}