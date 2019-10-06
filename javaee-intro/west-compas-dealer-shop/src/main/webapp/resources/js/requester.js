const http = (function () {

    const queryStringify = params => {
        if (!params) {
            return;
        }
        return Object.entries(params)
            .reduce((acc, entry, index) => {
                const [param, value] = entry;
                const encoded = index === 0
                    ? `${param}=${encodeURIComponent(value)}`
                    : `&${param}=${encodeURIComponent(value)}`;
                return `${acc}${encoded}`;
            }, '')
    };

    const setHeaders = (opts = {}) => {
        const headers = new Headers(opts.headers);
        if (opts.method !== 'get' && !headers.get('content-type')) {
            headers.set('content-type', 'application/json')
        }
        return headers
    };

    const createURL = opts => {
        const {url, params} = opts;
        if (!params) {
            return url;
        }
        return `${url}?${queryStringify(params)}`;
    };

    const formatBody = opts => {
        const type = opts.headers.get('content-type');

        if (!type) return;
        if (type.includes('json')) return JSON.stringify(opts.body);
        if (type.includes('x-www-form-urlencoded')) return queryStringify(opts.body);
        return opts.body
    };

    const setOptions = (options = {}) => {
        const opts = Object.assign({}, options);
        opts.url = createURL(opts);
        opts.method = opts.method || 'GET';
        opts.headers = setHeaders(opts);
        opts.body = formatBody(opts);
        return opts;
    };

    const call = function (url, options) {
        const opts = setOptions(Object.assign({url}, options));
        return fetch(opts.url, opts);
    };

    const get = (url, options) => {
        return call(url, options);
    };

    const post = (url, options) => {
        options.method = 'POST';
        return call(url, options);
    };

    const put = (url, options) => {
        options.method = 'PUT';
        return call(url, options);
    };

    const del = (url, options = {}) => {
        options.method = 'DELETE';
        return call(url, options);
    };

    return {
        get,
        post,
        put,
        del
    };

}());

// module.exports = http;