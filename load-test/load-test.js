import http from 'k6/http';
import { check, fail } from 'k6';

const TEST_TYPE = __ENV.TEST_TYPE || 'sync';

export const options = {
    stages: [
        { duration: '30s', target: 250 },  // Ramp up to 50 users
        { duration: '1m', target: 250 },   // Stay at 50 users for 1 minute
        { duration: '30s', target: 500 }, // Ramp up to 100 users
        { duration: '1m', target: 500 },  // Stay at 100 users for 1 minute
        { duration: '30s', target: 0 },   // Ramp down to 0 users
    ],
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(90)', 'p(95)', 'p(99)']
};

export default function() {
    if (TEST_TYPE === 'sync') {
        const response = http.get('http://localhost:8080/products/query?size=20');
        try {
            check(response, {
                'syncRequest OK': (res) => res.status === 200,
            });
        } catch (e) {
            fail(`Sync Request failed: ${e}`);
        }
    } else if (TEST_TYPE === 'async') {
        const response = http.get('http://localhost:8080/products/async/query?size=20');
        try {
            check(response, {
                'asyncRequest OK': (res) => res.status === 200,
            });
        } catch (e) {
            fail(`Async Request failed: ${e}`);
        }
    }
}