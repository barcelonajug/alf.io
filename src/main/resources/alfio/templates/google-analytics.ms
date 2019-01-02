function submitPageView() {
    if(window.ga) {
        ga('create', '{{account}}', 'auto', {'allowLinker': true, 'anonymizeIp': true, 'storage': 'none', 'clientId': '{{clientId}}'});
        ga('require', 'linker');
        ga('linker:autoLink', ['www.jbcnconf.com'] );
        ga('send', 'pageview');
    } else {
        window.setTimeout(submitPageView, 50);
    }
};
window.setTimeout(submitPageView, 50);
